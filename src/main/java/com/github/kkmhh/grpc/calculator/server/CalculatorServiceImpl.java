package com.github.kkmhh.grpc.calculator.server;

import com.proto.calculator.*;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void summation(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        //extracting value
        SumResponse response = SumResponse.newBuilder()
                .setResult(Integer.sum(request.getFirstNumber(), request.getLastNumber()))
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    @Override
    public void primeManyTimes(PrimeManyTimesRequest request, StreamObserver<PrimeManyTimesResponse> responseObserver) {
//        k = 2
//        N = 210
//        while N > 1:
//        if N % k == 0:   // if k evenly divides into N
//        print k      // this is a factor
//        N = N / k    // divide N by k so that we have the rest of the number left.
//    else:
//        k = k + 1
        int k = 2;
        int N = request.getNumber();
        while (N > 1) {
            if (N % k == 0) {
                responseObserver.onNext(PrimeManyTimesResponse.newBuilder().setNumber(k).build());
                N = N / k;
            } else {
                k++;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}
