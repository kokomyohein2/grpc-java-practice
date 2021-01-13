package com.github.kkmhh.grpc.greeting.client;

import com.proto.calculator.Sum;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import com.proto.calculator.SumServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("calculator client started");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        SumServiceGrpc.SumServiceBlockingStub sumClient = SumServiceGrpc.newBlockingStub(channel);

        Sum sum = Sum.newBuilder()
                .setFirstNum(44)
                .setLastNum(30)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder()
                .setSum(sum)
                .build();

        SumResponse sumResponse = sumClient.summation(sumRequest);
        System.out.println("res : " + sumResponse.getResult());
        
        channel.shutdown();
    }
}
