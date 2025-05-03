package com.example.assignment.service;

import com.example.assignment.util.Classification;
import com.example.assignment.model.DirectoryStructure;
import com.example.assignment.parser.CsvParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DirectoryServiceTest {

    @InjectMocks
    private DirectoryService directoryService;

    @Mock
    private CsvParser csvParser;

    private List<DirectoryStructure> mockDirectoryList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock directory data
        mockDirectoryList = Arrays.asList(
                new DirectoryStructure(1, null, "folder1", "Directory", "100", Classification.PUBLIC.getValue(),
                        "checksum1"),
                new DirectoryStructure(2, 1, "file1", "File", "50", Classification.SECRET.getValue(), "checksum2"),
                new DirectoryStructure(3, 1, "file2", "File", "30", Classification.TOP_SECRET.getValue(), "checksum3"),
                new DirectoryStructure(4, 1, "file3", "File", "20", Classification.PUBLIC.getValue(), "checksum4"),
                new DirectoryStructure(5, 1, "file4", "File", "40", Classification.SECRET.getValue(), "checksum5"));

        // Mock the CsvParser to return the mock directory list
        when(csvParser.parseCsv("directory-structure.csv")).thenReturn(mockDirectoryList);

        // Initialize the directory list in the service
        directoryService.loadDirectoryStructure("directory-structure.csv");
    }

    @Test
    void testGetPublicFilesSize() {
        long publicFilesSize = directoryService.getPublicFilesSize();
        assertEquals(120, publicFilesSize); // 100 (folder1) + 20 (file3)
    }

    @Test
    void testGetTopSecretFiles() {
        String topSecretFiles = directoryService.getTopSecretFiles();
        assertEquals("file2", topSecretFiles);
    }

    @Test
    void testGetSecretFiles() {
        String secretFiles = directoryService.getSecretFiles();
        assertEquals("file1\nfile4", secretFiles); // Sorted alphabetically
    }

    @Test
    void testGetSecretOrTopSecretFiles() {
        String secretOrTopSecretFiles = directoryService.getSecretOrTopSecretFiles();
        assertEquals("file1\nfile2\nfile4", secretOrTopSecretFiles); // Sorted alphabetically
    }

    @Test
    void testGetNonPublicFilesInFolder11() {
        String nonPublicFiles = directoryService.getNonPublicFilesInFolder11();
        assertEquals("", nonPublicFiles); // No non-public files in folder11 in mock data
    }
}