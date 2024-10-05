package com.example.checkContent.assembler;

import com.example.checkContent.controller.ContentController;
import com.example.checkContent.controller.ResponseController;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContentModelAssembler implements RepresentationModelAssembler<Content, EntityModel<ContentDTO>> {
    private final ModelMapper modelMapper;

    public ContentModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

//    @Override
//    public @NotNull EntityModel<ContentDTO> toModel(@NotNull Content content) {
//        ContentDTO contentDTO = modelMapper.map(content, ContentDTO.class);

//        EntityModel<ContentDTO> contentEntityModel = EntityModel.of(contentDTO
//                linkTo(methodOn(ContentController.class)
//                        .getContentById(content.getId()))
//                        .withSelfRel()
//        );

    @Override
    public @NotNull EntityModel<ContentDTO> toModel(@NotNull Content content) {
        ContentDTO contentDTO = modelMapper.map(content, ContentDTO.class);

        EntityModel<ContentDTO> contentEntityModel = EntityModel.of(contentDTO,
                linkTo(methodOn(ContentController.class).getContentById(content.getId())).withSelfRel()
        );

        switch (content.getStatus()) {
            case "WAITING":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId()))
                        .withRel("approve")
                        .withType("PATCH")
                        .withDeprecation("отправить контент на проверку"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update")
                        .withType("PATCH"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete")
                        .withType("DELETE"));
                break;

            case "APPROVED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).publishContent(content.getId()))
                        .withRel("publish")
                        .withType("PATCH")
                        .withDeprecation("отправить на публикацию"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update")
                        .withType("PATCH"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete")
                        .withType("DELETE"));
                break;

            case "REJECTED":
                contentEntityModel.add(linkTo(methodOn(ContentController.class).approveContent(content.getId()))
                        .withRel("re approve"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).updateContent(content.getId(), null))
                        .withRel("update"));
                contentEntityModel.add(linkTo(methodOn(ContentController.class).deleteContent(content.getId()))
                        .withRel("delete"));
                contentEntityModel.add(linkTo(methodOn(ResponseController.class).getResponseById(content.getId()))
                        .withRel("посмотреть ответ")
                        .withType("GET"));
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
