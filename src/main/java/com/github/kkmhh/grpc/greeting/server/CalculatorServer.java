package com.github.kkmhh.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Calculator Service started");

        Server server = ServerBuilder.forPort(50052)
                .addService(new CalculatorServiceImpl())
                .build();
        server.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("got req");
            server.shutdown();
            System.out.println("server stopped successfully");
        }));
        
        server.awaitTermination();
        
    }
}
