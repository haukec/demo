package com.example.demo.anotherhelloservice;

import org.lognet.springboot.grpc.GRpcService;

import com.example.demo.anotherhelloservice.AnotherHelloServiceGrpc.AnotherHelloServiceImplBase;
import com.example.demo.anotherhelloservice.Anotherhelloservice.AnotherHelloReply;
import com.example.demo.anotherhelloservice.Anotherhelloservice.AnotherHelloRequest;

import io.github.majusko.grpc.jwt.annotation.Allow;
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
