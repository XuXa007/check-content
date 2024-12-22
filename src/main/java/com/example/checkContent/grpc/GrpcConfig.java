package com.example.checkContent.grpc;

import com.example.contentanalysis.grpc.ClassificationContentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcConfig implements AutoCloseable{
    private final ManagedChannel channel;
    private final ClassificationContentServiceGrpc.ClassificationContentServiceBlockingStub blockingStub;

    public GrpcConfig() {
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        this.blockingStub = ClassificationContentServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void close() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }


//    public CategoryResponse classifyContent(String title, String content, String requestId) {
//        CategoryRequest request = CategoryRequest.newBuilder()
//                .setTitle(title)
//                .setContent(content)
//                .setRequestId(requestId)
//                .build();
//
//        return blockingStub.detectCategory(request);
//    }
}

