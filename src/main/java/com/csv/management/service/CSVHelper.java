package com.csv.management.service;

import com.csv.management.model.CSVData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<CSVData> csvToData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<CSVData> dataList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                dataList.add(getCsvData(csvRecord));
            }
            return dataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static CSVData getCsvData(CSVRecord csvRecord) {
        CSVData csvData = new CSVData();
        csvData.setCode(csvRecord.get("code"));
        csvData.setCodeListCode(csvRecord.get("codeListCode"));
        csvData.setDisplayValue(csvRecord.get("displayValue"));
        csvData.setFromDate(Date.valueOf(csvRecord.get("fromDate")));
        csvData.setLongDescription(csvRecord.get("longDescription"));
        csvData.setSortingPriority(Integer.parseInt(csvRecord.get("sortingPriority")));
        csvData.setSource(csvRecord.get("source"));
        csvData.setToDate(Date.valueOf(csvRecord.get("toDate")));
        return csvData;
    }
}
