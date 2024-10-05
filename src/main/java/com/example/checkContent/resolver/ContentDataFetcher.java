package com.example.checkContent.resolver;

import com.example.checkContent.dto.AddContentDTO;
import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.service.ContentService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class ContentDataFetcher {
    private final ContentService contentService;

    public ContentDataFetcher(ContentService contentService) {
        this.contentService = contentService;
    }

    @DgsQuery
    public List<ContentDTO> contents(@InputArgument String titleFilter) {
        List<ContentDTO> allContents = contentService.getAllContentDTO();

        if (titleFilter == null) {
            return allContents;
        }
        return allContents.stream()
                .filter(c -> c.getTitle().contains(titleFilter))
                .collect(Collectors.toList());
    }



    @DgsQuery
    public Optional<ContentDTO> getContentsByID(@InputArgument Long id) {
        return contentService.getContentByIdDTO(id);
    }

    @DgsMutation
    public ContentDTO addContent(@InputArgument AddContentDTO contentDTO) {
        ContentDTO content = new ContentDTO();
        content.setTitle(contentDTO.getTitle());
        content.setBody(contentDTO.getBody());
        content.setStatus("WAITING");
        content.setPublished(false);
        contentService.addContent(content);
        return content;
    }

}
