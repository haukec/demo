package com.example.demo.helloservice;

import org.lognet.springboot.grpc.GRpcService;

import com.example.demo.grpc.jwt.HelloReply;
import com.example.demo.grpc.jwt.HelloRequest;
import com.example.demo.grpc.jwt.HelloServiceGrpc;
import com.example.demo.grpc.jwt.HelloServiceGrpc.HelloServiceImplBase;
import com.example.demo.grpc.jwt.annotation.Allow;
import com.example.demo.grpc.jwt.data.GrpcJwtContext;
import com.example.demo.grpc.jwt.data.JwtContextData;

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
