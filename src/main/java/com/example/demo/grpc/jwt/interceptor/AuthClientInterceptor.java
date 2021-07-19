package com.example.demo.grpc.jwt.interceptor;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.grpc.jwt.data.GrpcHeader;
import com.example.demo.grpc.jwt.service.JwtService;

public class AuthClientInterceptor implements ClientInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtService jwtService;

    public AuthClientInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
        MethodDescriptor<ReqT, RespT> method,
        CallOptions callOptions,
        Channel next
    ) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, final Metadata metadata) {
                final Listener<RespT> tracingResponseListener = responseListener(responseListener);

                super.start(tracingResponseListener, injectInternalToken(metadata));
            }
        };
    }

    private <RespT> ForwardingClientCallListener<RespT> responseListener(ClientCall.Listener<RespT> responseListener) {
        return new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
            @Override
            public void onClose(Status status, Metadata metadata) {
                handleAuthStatusCodes(status);

                super.onClose(status, metadata);
            }
        };
    }

    private Metadata injectInternalToken(Metadata metadata) {
        final String authHeader = metadata.get(GrpcHeader.AUTHORIZATION);

        if(authHeader == null || authHeader.isEmpty()) {
            final String internalToken = jwtService.getInternal();
            metadata.put(GrpcHeader.AUTHORIZATION, internalToken);
        }

        return metadata;
    }

    private void handleAuthStatusCodes(Status status) {
        if(status.getCode().equals(Status.UNAUTHENTICATED.getCode())) {
            logger.error("Grpc call is unauthenticated.", status.getCause());
        }

        if(status.getCode().equals(Status.PERMISSION_DENIED.getCode())) {
            logger.error("Grpc call is unauthorized.", status.getCause());
        }
    }
}
