package com.example.studyshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDB() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "✅ Kết nối thành công tới MySQL: " + conn.getMetaData().getURL();
        }
    }
}
