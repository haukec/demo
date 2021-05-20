package com.example.demo;

import io.github.majusko.grpc.jwt.annotation.Allow;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

	@Override
	@Allow(roles= {"admin"})
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
