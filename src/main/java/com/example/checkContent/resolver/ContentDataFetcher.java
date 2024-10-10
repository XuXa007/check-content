package com.example.checkContent.resolver;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.service.ContentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class ContentDataFetcher {
    private final ContentService contentService;

    public ContentDataFetcher(ContentService contentService) {
        this.contentService = contentService;
    }

    @DgsQuery
    public List<ContentDTO> contents() {
        return contentService.getAllContentDTO();
    }

    @DgsQuery
    public Optional<ContentDTO> getContentsByID(@InputArgument Long id) {
        return contentService.getContentByIdDTO(id);
    }

    @DgsMutation
    public ContentDTO addContent(@InputArgument ContentDTO contentDTO) {
        return contentService.addContent(contentDTO);
    }

}


