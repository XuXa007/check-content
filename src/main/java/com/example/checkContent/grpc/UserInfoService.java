//package com.example.checkContent.grpc;
//
//import com.example.checkContent.model.User;
//import com.example.checkContent.repository.UserRepository;
//import com.example.grpc.GetUserRequest;
//import com.example.grpc.GetUserResponse;
//import com.example.grpc.UserInfo;
//import com.example.grpc.UserInfoServiceGrpc;
//import io.grpc.stub.StreamObserver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Stream;
//
//@Service
//public class UserInfoService extends UserInfoServiceGrpc.UserInfoServiceImplBase {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void getUserInfo(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
//        User user = userRepository.findById(request.getUserId()).orElse(null);
//
//
//        if (user!=null) {
//            GetUserResponse response = GetUserResponse.newBuilder()
//                    .setUserId(user.getId())
//                    .setUsername(user.getUsername())
//                    .setEmail(user.getEmail())
//                    .setRole(user.getRole().toString())
//                    .setStatus(user.getStatus().toString())
//                    .setRating(user.getRating())
//                    .setViolationCount(user.getViolationCount())
//                    .setTotalContents(user.getContents() != null ? user.getContents().size() : 0)
//                    .build();
//
//            responseObserver.onNext(response);
//        } else {
//            GetUserResponse response=GetUserResponse.newBuilder()
//                    .setUserId(request.getUserId())
//                    .setUsername("NOT_FOUND")
//                    .build();
//            responseObserver.onNext(response);
//        }
//
//        responseObserver.onCompleted();
//    }
//
//}
