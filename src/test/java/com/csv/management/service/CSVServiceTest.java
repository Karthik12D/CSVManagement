package com.csv.management.service;

import com.csv.management.model.CSVData;
import com.csv.management.repository.CSVRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CSVServiceTest {

    @InjectMocks
    CSVService csvService;

    @Mock
    CSVRepository csvRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        MultipartFile file = new MockMultipartFile("file", "hello.csv", "text/csv", "Hello,World".getBytes());
        CSVData csvData = getCsvData();
        when(csvRepository.saveAll(anyList())).thenReturn(Arrays.asList(csvData));

        List<CSVData> response = csvService.save(file);

        assertEquals(1, response.size());
        verify(csvRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testDeleteAllCodes() {
        doNothing().when(csvRepository).deleteAll();

        csvService.deleteAllCodes();

        verify(csvRepository, times(1)).deleteAll();
    }

    @Test
    public void testDeleteByCode() {
        doNothing().when(csvRepository).deleteById(anyLong());

        csvService.deleteByCode(123L);

        verify(csvRepository, times(1)).deleteById(123L);
    }

    @Test
    public void testGetDataByCode() {
        CSVData csvData = getCsvData();
        when(csvRepository.findAll()).thenReturn(Arrays.asList(csvData));

        CSVData response = csvService.getDataByCode("123");

        assertEquals(csvData, response);
        verify(csvRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllCodes() {
        CSVData csvData = new CSVData();
        when(csvRepository.findAll()).thenReturn(Arrays.asList(csvData));

        List<CSVData> response = csvService.getAllCodes();

        assertEquals(1, response.size());
        verify(csvRepository, times(1)).findAll();
    }

    private static CSVData getCsvData() {
        CSVData csvData = new CSVData();
        csvData.setCode("123");
        csvData.setCodeListCode("codeListCode");
        csvData.setDisplayValue("displayValue");
        csvData.setFromDate(new Date(0L));
        csvData.setLongDescription("longDescription");
        csvData.setSortingPriority(1);
        csvData.setSource("source");
        csvData.setToDate(null);
        return csvData;
    }
}