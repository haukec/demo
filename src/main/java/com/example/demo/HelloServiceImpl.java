package com.example.demo;

import com.example.demo.lib.HelloReply;
import com.example.demo.lib.HelloRequest;
import com.example.demo.lib.HelloServiceGrpc.HelloServiceImplBase;

import io.github.majusko.grpc.jwt.annotation.Allow;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceImplBase {

	@Override
	@Allow(roles= {"admin"})
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
}
