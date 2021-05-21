package com.example.demo.helloservice;

import org.lognet.springboot.grpc.GRpcService;

import io.github.majusko.grpc.jwt.annotation.Allow;
import io.github.majusko.grpc.jwt.data.GrpcJwtContext;
import io.github.majusko.grpc.jwt.data.JwtContextData;
import io.grpc.stub.StreamObserver;

@GRpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

	@Override
	@Allow(roles = {"admin", "operator"})
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        
        JwtContextData authContext = GrpcJwtContext.get().orElseThrow(RuntimeException::new);
        
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName() + "(" + authContext.getUserId() + ")").build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
}
