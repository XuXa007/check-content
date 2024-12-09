//package com.example.checkContent.grpc;
//
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//public class GrpcConfig {
////    @Bean
////    public Server grpcServer(UserInfoService userInfoService) throws IOException, InterruptedException {
////        Server server = ServerBuilder.forPort(8080).addService(userInfoService).build();
////        server.start();
////
////        System.out.println("Server started on port 8080");
////        server.awaitTermination();
////
////        return server;
////    }
//
//}
