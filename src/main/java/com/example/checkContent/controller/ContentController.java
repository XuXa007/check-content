package com.example.checkContent.controller;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.assembler.ContentModelAssembler;
import com.example.checkContent.service.ContentService;
import com.example.checkContent.service.ResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    static final String exchangeName="contentToModerationQueue";
    private final ContentService contentService;
    private final ResponseService responseService;
    private final ContentModelAssembler assembler;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ContentController(ContentService contentService, ResponseService responseService,
                             ContentModelAssembler assembler, ModelMapper modelMapper, RabbitTemplate rabbitTemplate) {
        this.contentService = contentService;
        this.responseService = responseService;
        this.assembler = assembler;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping
    public void addContent(@RequestBody ContentDTO contentDTO) {
//        rabbitTemplate.convertAndSend("contentToModerationQueue", contentDTO.getId());
        contentService.addContent(contentDTO);
    }

    @GetMapping
    public CollectionModel<EntityModel<ContentDTO>> getAllContent() {
        List<ContentDTO> contentDTOs = contentService.getAllContent();

        List<EntityModel<ContentDTO>> contentEntities = contentDTOs.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(contentEntities);
    }


    @GetMapping("/{id}")
    public EntityModel<ContentDTO> getContentById(@PathVariable Long id) {
        ContentDTO content = contentService.getContentById(id);
        return assembler.toModel(content);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<String> approveContent(@PathVariable Long id) {
        contentService.approveContent(id);

        ContentDTO content = contentService.getContentById(id);
        if (content.getStatus().equals("APPROVED")) {
            return ResponseEntity.ok("yes");
        } else if (content.getStatus().equals("REJECTED")) {
            return ResponseEntity.ok("no");
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
            return ResponseEntity.ok("updated successfully");

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
