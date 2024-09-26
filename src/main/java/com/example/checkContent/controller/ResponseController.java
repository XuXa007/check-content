package com.example.checkContent.controller;

import com.example.checkContent.assembler.ContentLinkBuilder;
import com.example.checkContent.model.Content;
import com.example.checkContent.model.Response;
import com.example.checkContent.model.User;
import com.example.checkContent.service.ContentService;
import com.example.checkContent.service.ResponseService;
import com.example.checkContent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    private final ResponseService responseService;
    private final UserService userService;
    private final ContentService contentService;
    private final ContentLinkBuilder assembler;


    @Autowired
    public ResponseController(ResponseService responseService, UserService userService, ContentService contentService, ContentLinkBuilder assembler) {
        this.responseService = responseService;
        this.userService = userService;
        this.contentService =contentService;
        this.assembler=assembler;
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
    public ResponseEntity<Response> getResponseById(@PathVariable Long id) {
        Response response = responseService.getResponseById(id).orElseThrow(() -> new RuntimeException("Response not found"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addResponse(@RequestParam Long userId, @RequestParam Long contentId, @RequestBody Response response) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("response not found"));
        Content content = contentService.getContentById(contentId).orElseThrow(() -> new RuntimeException("content not found"));

        response.setUser(user);
        response.setContent(content);

        responseService.saveResponse(response);

        return ResponseEntity.ok("ok");
    }
}
