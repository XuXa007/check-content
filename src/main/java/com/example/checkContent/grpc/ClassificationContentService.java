package com.example.checkContent.grpc;


import com.example.contentanalysis.grpc.ClassificationContentServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationContentService extends ClassificationContentServiceGrpc.ClassificationContentServiceImplBase {

    public void contentReturnAfterAll() {

    }

}
