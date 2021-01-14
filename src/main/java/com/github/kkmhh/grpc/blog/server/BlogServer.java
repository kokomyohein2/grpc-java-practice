package com.github.kkmhh.grpc.blog.server;

import com.github.kkmhh.grpc.calculator.server.CalculatorServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class BlogServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("BlogServer started");

        Server server = ServerBuilder.forPort(50051)
                .addService(new BlogServiceImpl())
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
