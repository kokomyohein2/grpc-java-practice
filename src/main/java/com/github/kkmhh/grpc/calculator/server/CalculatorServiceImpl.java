package com.github.kkmhh.grpc.calculator.server;

import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import com.proto.calculator.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends SumServiceGrpc.SumServiceImplBase {

    @Override
    public void summation(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        //extracting value
        SumResponse response = SumResponse.newBuilder()
                .setResult(Integer.sum(request.getFirstNumber(),request.getLastNumber()))
                .build();
        
        responseObserver.onNext(response);
        
        responseObserver.onCompleted();
    }
}
