package com.example.checkContent.repository;

import com.example.checkContent.model.Content;
import com.example.checkContent.model.Response;
import com.example.checkContent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByContent(Content content);
    List<Response> findByUser(User user);
}
