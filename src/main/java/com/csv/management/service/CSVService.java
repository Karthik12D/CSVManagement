package com.csv.management.service;

import com.csv.management.model.CSVData;
import com.csv.management.repository.CSVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVService {

    CSVRepository csvRepository;

    public List<CSVData> save(MultipartFile file) {
        try {
            List<CSVData> orders = CSVHelper.csvToData(file.getInputStream());
            return csvRepository.saveAll(orders);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void deleteAllCodes() {
        csvRepository.deleteAll();
    }

    public void deleteByCode(Long id) {
        csvRepository.deleteById(id);
    }

    public CSVData getDataByCode(String id) {
        return csvRepository.findAll().stream()
                .filter(csvData -> csvData.getCode().equals(id)).findFirst().orElse(null);
    }

    public List<CSVData> getAllCodes() {
        return csvRepository.findAll();
    }

}
