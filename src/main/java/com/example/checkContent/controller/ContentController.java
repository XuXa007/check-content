package com.example.checkContent.controller;

import com.example.checkContent.model.Content;
import com.example.checkContent.assembler.ContentLinkBuilder;
import com.example.checkContent.model.Response;
import com.example.checkContent.service.ContentService;
import com.example.checkContent.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private ContentLinkBuilder assembler;

    @PostMapping
    public ResponseEntity<String> addContent(@RequestBody Content content) {
        Content newContent = contentService.addContent(content.getTitle(), content.getBody());
        return ResponseEntity.ok("add");
    }

    @GetMapping
    public CollectionModel<EntityModel<Content>> getAllContent() {
        List<Content> contents = contentService.getAllContent();
        List<EntityModel<Content>> contentModels = contents
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(contentModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Content>> getContentById(@PathVariable Long id) {
        Content content=contentService.getContentById(id).orElseThrow(RuntimeException::new);

        if ("REJECTED".equals(content.getStatus())) {
            List<Response> responses = responseService.getResponsesByContent(content);

            if (!responses.isEmpty()) {
                EntityModel<Content> contentModel = assembler.toModel(content);
                // Добавляем ссылку на отклик
                contentModel.add(linkTo(methodOn(ResponseController.class).getResponseById(responses.get(0).getId())).withRel("response"));

                return ResponseEntity.ok(contentModel);
            }
        }

        EntityModel<Content> contentModel = assembler.toModel(content);
        return ResponseEntity.ok(contentModel);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<String> approveContent(@PathVariable Long id) {
        String res= contentService.approveContent(id);

        if (res.startsWith("approved")) {
            return ResponseEntity.ok("Success: Content approved");
        } else if (res.startsWith("rejected")) {
            return ResponseEntity.badRequest().body(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/published/{id}")
    public ResponseEntity<String> publishContent(@PathVariable Long id){
        boolean suc=contentService.publishContent(id);
        if (suc) {
            return ResponseEntity.ok("Suc published");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateContent(@PathVariable Long id, @RequestBody Content updatedContent) {
        boolean suc=contentService.updateContent(id, updatedContent.getTitle(), updatedContent.getBody());
        if (suc) {
            return ResponseEntity.ok(" updated successfully");

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable Long id){
        boolean suc=contentService.deleteContent(id);
        if (suc) {
            return ResponseEntity.ok("delete");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/published")
    public CollectionModel<EntityModel<Content>> getPublishedContent() {
        List<Content> contents = contentService.getPublishedContent();
        List<EntityModel<Content>> contentModels = contents
                .stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(contentModels);
    }

}
