package com.example.demo;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.grpc.jwt.data.GrpcHeader;
import com.example.demo.grpc.jwt.interceptor.AuthClientInterceptor;
import com.example.demo.grpc.jwt.service.JwtService;
import com.example.demo.grpc.jwt.service.dto.JwtData;

import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private JwtService jwtService;
	   
    @Autowired
    private AuthClientInterceptor authClientInterceptor;
	
//    @Test
//    public void testSuccessInternalToken() throws IOException {
//
//        final String token = jwtService.generate(new JwtData("some-user-id", "operator"));
//
//        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",6565).usePlaintext().build();
//        final Channel interceptedChannel = ClientInterceptors.intercept(channel,authClientInterceptor);
//        final HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(interceptedChannel);
//        
//        final Metadata header = new Metadata();
//        header.put(GrpcHeader.AUTHORIZATION, token);
//        
//        HelloServiceGrpc.HelloServiceBlockingStub injectedStub = MetadataUtils.attachHeaders(stub, header);
//        
//        System.err.println(injectedStub.sayHello(HelloRequest.newBuilder().setName("Test").build()).getMessage());
//    }
}
