package com.example.demo.helloservice;

import org.lognet.springboot.grpc.GRpcService;

import com.example.demo.grpc.jwt.AnotherHelloReply;
import com.example.demo.grpc.jwt.AnotherHelloRequest;
import com.example.demo.grpc.jwt.AnotherHelloServiceGrpc;
import com.example.demo.grpc.jwt.AnotherHelloServiceGrpc.AnotherHelloServiceImplBase;
import com.example.demo.grpc.jwt.annotation.Allow;

import io.grpc.stub.StreamObserver;

@GRpcService
public class AnotherHelloServiceImpl extends AnotherHelloServiceImplBase {

	@Override
	@Allow(roles = {"admin", "operator"})
	public void sayHello(AnotherHelloRequest request, StreamObserver<AnotherHelloReply> responseObserver) {
        AnotherHelloReply reply = AnotherHelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
}
