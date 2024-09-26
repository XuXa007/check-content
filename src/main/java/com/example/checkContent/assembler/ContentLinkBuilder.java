package com.example.checkContent.assembler;

import com.example.checkContent.controller.ContentController;
import com.example.checkContent.model.Content;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContentLinkBuilder implements RepresentationModelAssembler<Content, EntityModel<Content>> {

    @Override
    public EntityModel<Content> toModel(Content content) {
        EntityModel<Content> contentEntityModel = EntityModel.of(content);
        contentEntityModel.add(linkTo(methodOn(ContentController.class).getContentById(content.getId())).withSelfRel());
        switch (content.getStatus()) {
            case "WAITING":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId()))
                        .withRel("approve")
                        .withDeprecation("отправить контент на проверку"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete"));
                break;
            case "APPROVED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).publishContent(content.getId()))
                        .withRel("publish").withDeprecation("отправить на публикацию"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete"));
                break;
            case "REJECTED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId()))
                        .withRel("re approve"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete"));
                break;

            case "PUBLISHED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete"));
                break;
        }
        return contentEntityModel;
    }
}


