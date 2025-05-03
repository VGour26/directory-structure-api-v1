package com.example.assignment.controller;

import com.example.assignment.service.DirectoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.assignment.util.Classification;

@RestController
@RequestMapping("/api/directory")
public class DirectoryController {

    @Autowired
    private DirectoryService directoryService;

    @GetMapping("/tree")
    public String getIndentedTree() {
        return directoryService.getIndentedTree();
    }

    @GetMapping("/top-secret")
    public String getTopSecretFiles() {
        return directoryService.getTopSecretFiles();
    }

    @GetMapping("/secret")
    public String getSecretFiles() {
        return directoryService.getSecretFiles();
    }

    @GetMapping("/secret-or-top-secret")
    public String getSecretOrTopSecretFiles() {
        return directoryService.getSecretOrTopSecretFiles();
    }

    @GetMapping("/public-size")
    public long getPublicFilesSize() {
        return directoryService.getPublicFilesSize();
    }

    @GetMapping("/non-public-folder11")
    public String getNonPublicFilesInFolder11() {
        return directoryService.getNonPublicFilesInFolder11();
    }

    @GetMapping("/files")
    public String getFilesByClassification(@RequestParam("type") List<String> classificationTypes) {
        return directoryService.getFilesByClassification(Classification.fromValues(classificationTypes));
    }

    @GetMapping("/find")
    public Object handleAction(
            @RequestParam("operation") String operation,
            @RequestParam(value = "classificationType", required = false) String classificationType) {

        switch (operation.toLowerCase()) {
            case "tree":
                return directoryService.getIndentedTree();
            case "public-size":
                return directoryService.getPublicFilesSize();
            case "non-public-folder11":
                return directoryService.getNonPublicFilesInFolder11();
            case "files-by-classification":
                if (classificationType == null) {
                    throw new IllegalArgumentException(
                            "classificationType query parameter is required for this operation.");
                }
                // Wrap the single Classification into a List
                return directoryService.getFilesByClassification(
                        List.of(Classification.fromValue(classificationType)));
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}
