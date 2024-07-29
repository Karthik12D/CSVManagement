package com.csv.management.service;

import com.csv.management.model.CSVData;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVHelperTest {

    @Test
    public void testHasCSVFormat() {
        MultipartFile file = new MockMultipartFile("file", "hello.csv", "text/csv", "Hello,World".getBytes());
        assertTrue(CSVHelper.hasCSVFormat(file));

        file = new MockMultipartFile("file", "hello.txt", "text/plain", "Hello,World".getBytes());
        assertFalse(CSVHelper.hasCSVFormat(file));
    }

    @Test
    public void testCsvToData() {
        String csvData = "code,codeListCode,displayValue,fromDate,longDescription,sortingPriority,source,toDate\n" +
                "123,codeListCode,displayValue,2022-01-01,longDescription,1,source,2022-12-31";
        InputStream is = new ByteArrayInputStream(csvData.getBytes());

        List<CSVData> dataList = CSVHelper.csvToData(is);

        assertEquals(1, dataList.size());
        CSVData data = dataList.get(0);
        assertEquals("123", data.getCode());
        assertEquals("codeListCode", data.getCodeListCode());
        assertEquals("displayValue", data.getDisplayValue());
        // Add more assertions for other fields
    }
}