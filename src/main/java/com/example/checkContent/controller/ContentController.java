package com.example.checkContent.controller;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.assembler.ContentLinkBuilder;
import com.example.checkContent.service.ContentService;
import com.example.checkContent.service.ResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;
    private final ResponseService responseService;
    private final ContentLinkBuilder assembler;
    private final ModelMapper modelMapper;

    @Autowired
    public ContentController(ContentService contentService, ResponseService responseService, ContentLinkBuilder assembler, ModelMapper modelMapper) {
        this.contentService = contentService;
        this.responseService = responseService;
        this.assembler = assembler;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    public void addContent(@RequestBody ContentDTO contentDTO) {
        Content content = modelMapper.map(contentDTO, Content.class);
        contentService.addContent(content);
    }

    @GetMapping
    public CollectionModel<EntityModel<ContentDTO>> getAllContent() {
        List<Content> contents = contentService.getAllContent();
        List<EntityModel<ContentDTO>> contentDTOs = contents.stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(contentDTOs);
    }

    @GetMapping("/{id}")
    public EntityModel<ContentDTO> getContentById(@PathVariable Long id) {
        Content content = contentService.getContentById(id).orElseThrow(RuntimeException::new);
        return assembler.toModel(content);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<String> approveContent(@PathVariable Long id) {
        contentService.approveContent(id);
        Optional<Content> content=contentService.getContentById(id);
        if (Optional.ofNullable(content.get().getStatus()).equals("APPROVED")){
            return ResponseEntity.ok().body("yes");
        } else if (Optional.ofNullable(content.get().getStatus()).equals("REJECTED")) {
            return ResponseEntity.ok().body("no");
        }
        return ResponseEntity.notFound().build();
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

//    @GetMapping("/published")
//    public CollectionModel<EntityModel<ContentDTO>> getPublishedContent() {
//        List<ContentDTO> contents = contentService.getPublishedContent();
//        List<EntityModel<ContentDTO>> contentDTOs = contents.stream()
//                .map(assembler::toModel)
//                .toList();
//        return CollectionModel.of(contentDTOs);
//    }
}
