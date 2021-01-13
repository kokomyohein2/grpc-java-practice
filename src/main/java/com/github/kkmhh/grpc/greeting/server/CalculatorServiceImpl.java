package com.github.kkmhh.grpc.greeting.server;

import com.proto.calculator.Sum;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import com.proto.calculator.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends SumServiceGrpc.SumServiceImplBase {

    @Override
    public void summation(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        //extracting value
        Sum sum = request.getSum();
        int num1 = sum.getFirstNum();
        int num2 = sum.getLastNum();

        int result = num1 + num2;
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();
        
        responseObserver.onNext(response);
        
        responseObserver.onCompleted();
    }
}
