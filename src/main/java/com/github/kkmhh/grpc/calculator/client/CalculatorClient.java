package com.github.kkmhh.grpc.calculator.client;

import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import com.proto.calculator.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("calculator client started");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        SumServiceGrpc.SumServiceBlockingStub sumClient = SumServiceGrpc.newBlockingStub(channel);

        SumRequest sumRequest = SumRequest.newBuilder()
                .setFirstNumber(10)
                .setLastNumber(20)
                .build();

        SumResponse sumResponse = sumClient.summation(sumRequest);
        System.out.printf("%d + %d = %d%n", sumRequest.getFirstNumber(), sumRequest.getLastNumber(), sumResponse.getResult());

        channel.shutdown();
    }
}
