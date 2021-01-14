package com.github.kkmhh.grpc.blog.client;

import com.proto.blog.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BlogClient {

    public static void main(String[] args) {
        System.out.println("Hello i am gRPC client");
        BlogClient main = new BlogClient();
        main.run();
    }

    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

//        insertBlog(channel);
//        getBlog(channel);

        System.out.println("Shutting down channel");
        channel.shutdown();
    }

    private void insertBlog(ManagedChannel channel) {
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);

        Blog blog = Blog.newBuilder()
                .setAutherId("KoKo")
                .setTitle("new blog")
                .setContent("hello this is first blog")
                .build();

        CreateBlogResponse response = client.createBlog(CreateBlogRequest.newBuilder()
                .setBlog(blog)
                .build());
        System.out.println("Received response");
        System.out.println(response.toString());
    }

    private void getBlog(ManagedChannel channel) {
        BlogServiceGrpc.BlogServiceBlockingStub client = BlogServiceGrpc.newBlockingStub(channel);

        ReadBlogResponse response = client.readBlog(ReadBlogRequest.newBuilder()
                .setBlogId("600020e07574413ddb56067e")
                .build());
        System.out.println("Received response");
        System.out.println(response.toString());        
    }
}