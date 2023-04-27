package com.understanding.springsecurity21.security;

import com.understanding.springsecurity21.model.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentAuthorizationManager {

    public boolean hasDocumentPermission(List<Document> returnedList, String authority){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean docsBelongToAuthUser = returnedList.stream()
                .allMatch(document -> document.getUsername().equals(username)); // All documents returned by service
        // method belongs to the authenticated user

        boolean hasProperAuthority = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.equals(new SimpleGrantedAuthority(authority)));

        return docsBelongToAuthUser && hasProperAuthority;
    }
}
