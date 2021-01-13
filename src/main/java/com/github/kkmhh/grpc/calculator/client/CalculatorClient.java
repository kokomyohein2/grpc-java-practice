package com.github.kkmhh.grpc.calculator.client;

import com.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

    public static void main(String[] args) {
        System.out.println("calculator client started");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorClient = CalculatorServiceGrpc.newBlockingStub(channel);

        // Unary start
//        SumRequest sumRequest = SumRequest.newBuilder()
//                .setFirstNumber(10)
//                .setLastNumber(20)
//                .build();
//
//        SumResponse sumResponse = sumClient.summation(sumRequest);
//        System.out.printf("%d + %d = %d%n", sumRequest.getFirstNumber(), sumRequest.getLastNumber(), sumResponse.getResult());
        // Unary end

        PrimeNumberDecompositionRequest primeManyTimesRequest = PrimeNumberDecompositionRequest.newBuilder()
                .setNumber(120)
                .build();

        calculatorClient.primeManyTimes(primeManyTimesRequest)
                .forEachRemaining(primeManyTimesResponse -> System.out.println(primeManyTimesResponse.getNumber()));

        channel.shutdown();
    }
}
