//package com.understanding.springsecurity21.security;
//
//import com.understanding.springsecurity21.model.Document;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.io.Serializable;
//import java.util.List;
//
//public class DocumentPermissionEvaluator implements PermissionEvaluator {
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        List<Document> returnedList = (List<Document>) targetDomainObject;
//        String username = authentication.getName();
//        String authority = (String) permission;
//
//        boolean docsBelongToAuthUser = returnedList.stream()
//                .allMatch(document -> document.getUsername().equals(username)); // All documents returned by service
//                                                                            // method belongs to the authenticated user
//
//        boolean hasProperAuthority = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.equals(new SimpleGrantedAuthority(authority)));
//
//        return docsBelongToAuthUser && hasProperAuthority;
//    }
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        return false;
//    }
//}
