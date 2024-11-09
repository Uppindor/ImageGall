package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class ReportControll {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/generate-report1")
    public String generateReport1() {
        return generateReport("SELECT name, description FROM halls", "report1.txt");
    }

    @GetMapping("/generate-report2")
    public String generateReport2() {
        return generateReport("SELECT columnA, columnB, columnC FROM table2", "report2.csv");
    }

    @GetMapping("/generate-report3")
    public String generateReport3() {
        return generateReport("SELECT columnX, columnY, columnZ FROM table3", "report3.csv");
    }

    private String generateReport(String sqlQuery, String fileName) {
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop" + File.separator + fileName;

        try (FileWriter writer = new FileWriter(desktopPath)) {
            jdbcTemplate.query(sqlQuery, (ResultSet rs) -> {
                int columnCount = rs.getMetaData().getColumnCount();

                // Write column names
                for (int i = 1; i <= columnCount; i++) {
                    try {
                        writer.write(rs.getMetaData().getColumnName(i));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (i < columnCount) {
                        try {
                            writer.write(",");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    writer.write("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Write rows
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        try {
                            writer.write(rs.getString(i));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (i < columnCount) {
                            try {
                                writer.write(",");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try {
                        writer.write("\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            writer.flush();
            return "Report " + fileName + " generated successfully on desktop!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error generating report: " + e.getMessage();
        }
    }

}
