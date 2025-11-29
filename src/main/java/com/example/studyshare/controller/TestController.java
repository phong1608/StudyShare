package com.example.studyshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private S3Client s3Client;
    @GetMapping("/test-db")
    public String testDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "Kết nối thành công tới MySQL: " + conn.getMetaData().getURL();
        }
    }
    @GetMapping("/test-s3")
    public List<String> testConnection() {
        List<String> bucketNames = new ArrayList<>();

        try {
            var response = s3Client.listBuckets();

            response.buckets().forEach(b -> bucketNames.add(b.name()));

            System.out.println(" Đã kết nối S3 thành công!");
            return bucketNames;

        } catch (Exception e) {
            System.out.println(" Lỗi kết nối S3: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
