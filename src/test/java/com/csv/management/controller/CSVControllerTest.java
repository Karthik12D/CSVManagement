package com.csv.management.controller;

import com.csv.management.model.CSVData;
import com.csv.management.model.ResponseMessage;
import com.csv.management.service.CSVService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CSVControllerTest {

    @InjectMocks
    CSVController csvController;

    @Mock
    CSVService csvService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadFile() {
        MultipartFile file = new MockMultipartFile("file", "hello.csv", "text/csv", "Hello,World".getBytes());
        when(csvService.save(file)).thenReturn(Arrays.asList(getCsvData()));

        ResponseEntity<ResponseMessage> response = csvController.uploadFile(file);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(csvService, times(1)).save(file);
    }

    @Test
    public void testGetAllCodes() {
        when(csvService.getAllCodes()).thenReturn(Arrays.asList(getCsvData()));

        ResponseEntity<List<CSVData>> response = csvController.getAllCodes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(csvService, times(1)).getAllCodes();
    }

    @Test
    public void testGetDataByCode() {
        CSVData csvData = getCsvData();
        when(csvService.getDataByCode(anyString())).thenReturn(csvData);

        ResponseEntity<List<CSVData>> response = csvController.getDataByCode("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(csvService, times(1)).getDataByCode("123");
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

    @Test
    public void testDeleteByCode() {
        doNothing().when(csvService).deleteByCode(anyLong());

        ResponseEntity<HttpStatus> response = csvController.deleteByCode(123L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(csvService, times(1)).deleteByCode(123L);
    }

    @Test
    public void testDeleteAllCodes() {
        doNothing().when(csvService).deleteAllCodes();
        ResponseEntity<HttpStatus> response = csvController.deleteAllCodes();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(csvService, times(1)).deleteAllCodes();
    }
}