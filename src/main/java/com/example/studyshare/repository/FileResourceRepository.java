package com.example.studyshare.repository;

import com.example.studyshare.entities.FileResource;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileResourceRepository extends JpaRepository<FileResource,Long> {
    List<FileResource> findByCategoryId(Long categoryCategoryId);

}
