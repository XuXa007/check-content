package com.example.checkContent.controller;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;
    private final ContentModelAssembler assembler;

    @Autowired
    public ContentController(ContentService contentService, ContentModelAssembler assembler) {
        this.contentService = contentService;
        this.assembler = assembler;
    }




    @PostMapping
    public EntityModel<ContentDTO> addContent(@RequestBody ContentDTO contentDTO) {
        ContentDTO content = contentService.addContent(contentDTO);
        return assembler.toModel(content);
    }

    @GetMapping
    public CollectionModel<EntityModel<ContentDTO>> getAllContent() {
        List<ContentDTO> contents = contentService.getAllContent();
        List<EntityModel<ContentDTO>> contentModels = contents.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(contentModels);
    }

    @GetMapping("/{id}")
    public EntityModel<ContentDTO> getContentById(@PathVariable Long id) {
        ContentDTO content = contentService.getContentById(id);
        return assembler.toModel(content);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<EntityModel<ContentDTO>> approveContent(@PathVariable Long id) {
//        contentService.approveContent(id);
//
//        ContentDTO content = contentService.getContentById(id);
//        if (content.getStatus().equals("APPROVED")) {
//            return ResponseEntity.ok("yes");
//        } else if (content.getStatus().equals("REJECTED")) {
//            return ResponseEntity.ok("no");
//        }
//        return ResponseEntity.notFound().build();
        ContentDTO content = contentService.approveContent(id);
        return ResponseEntity.ok(assembler.toModel(content));
    }


    @PatchMapping("/published/{id}")
    public ResponseEntity<EntityModel<ContentDTO>> publishContent(@PathVariable Long id){
        ContentDTO publish = contentService.publishContent(id);
        return ResponseEntity.ok(assembler.toModel(publish));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EntityModel<ContentDTO>> updateContent(@PathVariable Long id, @RequestBody Content updatedContent) {
        boolean suc = contentService.updateContent(id, updatedContent.getTitle(), updatedContent.getBody());
        if (suc) {
            ContentDTO update=contentService.getContentById(id);
            return ResponseEntity.ok(assembler.toModel(update));
        }
        return ResponseEntity.notFound().build();
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

//    @GetMapping("/published")
//    public CollectionModel<EntityModel<ContentDTO>> getPublishedContent() {
//        List<ContentDTO> contents = contentService.getPublishedContent();
//        List<EntityModel<ContentDTO>> contentDTOs = contents.stream()
//                .map(assembler::toModel)
//                .toList();
//        return CollectionModel.of(contentDTOs);
//    }
}
