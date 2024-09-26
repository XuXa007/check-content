package com.example.checkContent.controller;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {
    @GetMapping("/")
    public Link index() {
        Link link = linkTo(methodOn(ContentController.class).getAllContent()).withRel("content");
//        Link linkUser = linkTo(methodOn(ResponseController.class)).ge
        return link;
    }
}
