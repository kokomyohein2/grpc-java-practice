package com.github.kkmhh.grpc.greeting.server;

import com.proto.greet.*;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        //extracting value
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        // create response
        String result = "Hello " + firstName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        // send response
        responseObserver.onNext(response);

        // complete RPC call
        responseObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {
        IntStream.range(1, 10).forEach(i -> {
            responseObserver.onNext(GreetManyTimesResponse.newBuilder()
                    .setResult(String.format("Hello %s, response number: %d", request.getGreeting().getFirstName(), i))
                    .build());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        responseObserver.onCompleted();
    }
}
