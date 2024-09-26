package com.example.checkContent.repository;

import com.example.checkContent.dto.ContentDTO;
import com.example.checkContent.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<ContentDTO> findAllByPublishedTrue();
}


