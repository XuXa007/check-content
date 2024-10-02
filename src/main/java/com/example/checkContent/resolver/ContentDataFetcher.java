package com.example.checkContent.resolver;

import com.example.checkContent.dto.AddContentDTO;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import com.example.checkContent.service.ContentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ContentDataFetcher {
    private final ContentService contentService;

    public ContentDataFetcher(ContentService contentService) {
        this.contentService = contentService;
    }

    @DgsQuery
    public List<Content> contents(@InputArgument String titleFilter) {
        List<Content> allContents = contentService.getAllContent();

        if(titleFilter == null) {
            return allContents;
        }
        return allContents.stream()
                .filter(content -> content.getTitle().contains(titleFilter))
                .collect(Collectors.toList());
    }

    @DgsMutation
    public Content addContent(@InputArgument AddContentDTO contentDTO) {
        Content content = new Content();
        content.setTitle(contentDTO.getTitle());
        content.setBody(contentDTO.getBody());
        content.setStatus("WAITING");
        content.setPublished(false);
        contentService.addContent(content);
        return content;
    }

}
