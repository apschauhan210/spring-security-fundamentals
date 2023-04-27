package com.understanding.springsecurity21.services;

import com.understanding.springsecurity21.model.Document;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @PostAuthorize("hasPermission(returnObject, 'read')")
    public List<Document> finDocuments(String username) {
        Document document = new Document();
        document.setUsername("anuj");
        document.setText("Hi, \n Anuj!!\u200E\uD83D\uDE03 \n How are you buddy?");
        return List.of(document);
    }

}
