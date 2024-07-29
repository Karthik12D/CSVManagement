package com.csv.management.repository;

import com.csv.management.model.CSVData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSVRepository extends JpaRepository<CSVData, Long> {
}
