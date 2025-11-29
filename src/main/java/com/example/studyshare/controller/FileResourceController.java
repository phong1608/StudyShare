package com.example.studyshare.controller;

import com.example.studyshare.entities.FileResource;
import com.example.studyshare.services.FileResourceService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileResourceController {
    @Autowired
    private FileResourceService fileResourceService;

    @PostMapping("/upload")
    public ResponseEntity<?> UploadFile(@RequestParam("file") MultipartFile file, @RequestParam("category") Long categoryId, HttpServletRequest request) throws IOException {
        String userName = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        fileResourceService.create(file,categoryId,userName);
        return ResponseEntity.ok("Success");
    }
    @GetMapping("/category/{categoryId}")
    public List<FileResource> findByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return fileResourceService.findByCategoryId(categoryId);
    }


}
