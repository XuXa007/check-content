package com.example.checkContent.controller;

import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.dto.ResponseDTO;
import com.example.checkContent.model.Response;
import com.example.checkContent.service.ContentService;
import com.example.checkContent.service.ResponseService;
import com.example.checkContent.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    private final ResponseService responseService;
    private final UserService userService;
    private final ContentService contentService;
    private final ContentModelAssembler assembler;
    private final ContentController contentController;
    private final ModelMapper modelMapper;

    @Autowired
    public ResponseController(ResponseService responseService, UserService userService, ContentService contentService, ContentModelAssembler assembler, ContentController contentController, ModelMapper modelMapper) {
        this.responseService = responseService;
        this.userService = userService;
        this.contentService =contentService;
        this.assembler=assembler;
        this.contentController = contentController;
        this.modelMapper = modelMapper;
    }


//    @GetMapping("/")
//    public CollectionModel<EntityModel<Response>> getAllResponse() {
//        List<Response> responses = responseService.getAllResponse();
//        List<EntityModel<Response>> res = responses
//                .stream()
//                .map(assembler::toModel)
//                .toList();
//
//        return CollectionModel.of(res);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ResponseDTO>> getResponseById(@PathVariable Long id) {
        Response response = responseService.getResponseById(id).orElseThrow(RuntimeException::new);
        ResponseDTO responseDTO = modelMapper.map(response, ResponseDTO.class);

        EntityModel<ResponseDTO> responseDTOEntityModel = EntityModel.of(responseDTO);

        responseDTOEntityModel.add(linkTo(methodOn(ResponseController.class).getResponseById(id)).withSelfRel());
        responseDTOEntityModel.add(linkTo(methodOn(ContentController.class).getContentById(response.getContent().getId())).withRel("content"));


        return ResponseEntity.ok(responseDTOEntityModel);
    }
}
