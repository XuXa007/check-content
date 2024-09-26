package com.example.checkContent.service;

import com.example.checkContent.model.Content;
import com.example.checkContent.model.Response;
import com.example.checkContent.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;


    public Optional<Response> getResponseById(Long id) {
        return responseRepository.findById(id);
    }

    public void saveResponse(Response response) {
        responseRepository.save(response);
    }

    public List<Response> getAllResponse() {
        return responseRepository.findAll();
    }

}
