package com.understanding.springsecurity21.controllers;

import com.understanding.springsecurity21.model.Document;
import com.understanding.springsecurity21.services.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private final DocumentService documentService;

    public DemoController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{username}")
    public List<Document> findDocuments(@PathVariable String username){
        return documentService.finDocuments(username);
    }
}
