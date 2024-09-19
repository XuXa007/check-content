package com.example.checkContent.controller;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.assembler.ContentLinkBuilder;
import com.example.checkContent.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

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
    public List<Content> getPublishedContent() {
        return contentService.getPublishedContent();
    }

}
