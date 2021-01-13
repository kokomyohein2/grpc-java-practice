package com.github.kkmhh.grpc.greeting.client;

import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello i am gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //Unary start
//        Greeting greeting = Greeting.newBuilder()
//                .setFirstName("KoKo")
//                .setLastName("Unknown")
//                .build();
//
//        GreetRequest greetRequest = GreetRequest.newBuilder()
//                .setGreeting(greeting)
//                .build();
//
//        GreetResponse greetResponse = greetClient.greet(greetRequest);
//
//        System.out.println("Response : " + greetResponse.getResult());
        //Unary end

        GreetManyTimesRequest greetManyTimesRequest = GreetManyTimesRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("KoKo").build())
                .build();

        greetClient.greetManyTimes(greetManyTimesRequest)
                .forEachRemaining(greetManyTimesResponse -> System.out.println(greetManyTimesResponse.getResult()));


        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
