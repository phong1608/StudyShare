package com.example.studyshare.services;

import com.example.studyshare.entities.Admin;
import com.example.studyshare.entities.Category;
import com.example.studyshare.entities.FileResource;
import com.example.studyshare.repository.FileResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileResourceService {
    private final FileResourceRepository fileResourceRepository;
    private final S3Service s3Service;
    private final CategoryService categoryService;
    private final AdminService adminService;

    @Autowired
    public  FileResourceService(FileResourceRepository fileResourceRepository, S3Service s3Service, CategoryService categoryService, AdminService adminService) {
        this.fileResourceRepository = fileResourceRepository;
        this.s3Service = s3Service;
        this.categoryService = categoryService;
        this.adminService = adminService;
    }

    public List<FileResource> findByCategoryId(Long categoryId) {
        return fileResourceRepository.findByCategoryId(categoryId);
    }
    public FileResource findById(Long id) {
        return fileResourceRepository.findById(id).orElse(null);
    }
    public FileResource create(MultipartFile multipartFile, Long categoryId, String userName) throws IOException {

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category not found with id: " + categoryId);
        }
        Admin admin = adminService.getAdminByUserName(userName);
        if (admin == null) {
            throw new RuntimeException("Admin not found with name: " + userName);
        }
        String url = s3Service.UploadFile(multipartFile);

        FileResource fileResource = new FileResource();
        fileResource.setFilePath(url);
        fileResource.setFileSize(multipartFile.getSize());
        fileResource.setTitle(multipartFile.getOriginalFilename());
        fileResource.setCategory(category);
        fileResource.setAdmin(admin);
        fileResource.setUploadDate(LocalDateTime.now());
        return fileResourceRepository.save(fileResource);
    }

}
