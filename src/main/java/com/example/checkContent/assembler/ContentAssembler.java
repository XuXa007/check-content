package com.example.checkContent.assembler;

import com.example.checkContent.controller.ContentController;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContentAssembler implements RepresentationModelAssembler<Content, ContentDTO> {

    @Override
    public ContentDTO toModel(Content content) {
        ContentDTO contentEntityModel = new ContentDTO(content.getId(), content.getTitle(), content.getBody(), content.getStatus(), content.isPublished());
        contentEntityModel.add(linkTo(methodOn(ContentController.class).getContentById(content.getId())).withSelfRel());
        switch (content.getStatus()) {
            case "WAITING":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId())).withRel("approve"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null)).withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId())).withRel("delete"));
                break;
            case "APPROVED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).publishContent(content.getId())).withRel("publish"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null)).withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId())).withRel("delete"));
                break;
            case "REJECTED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId())).withRel("re approve"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null)).withRel("update"));

                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId())).withRel("delete"));
                break;

            case "PUBLISHED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null)).withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId())).withRel("delete"));
                break;
        }
        return contentEntityModel;
    }

}
