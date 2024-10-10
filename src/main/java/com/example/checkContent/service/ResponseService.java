package com.example.checkContent.service;

import com.example.checkContent.dto.ResponseDTO;
import com.example.checkContent.model.Response;
import com.example.checkContent.repository.ResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Optional<Response> getResponseById(Long id) {
        return responseRepository.findById(id);
    }

    public void addResponse(ResponseDTO responseDTO) {
        Response response=modelMapper.map(responseDTO, Response.class);
        responseRepository.save(response);
    }

    public List<Response> getAllResponse() {
        return responseRepository.findAll();
    }

}
