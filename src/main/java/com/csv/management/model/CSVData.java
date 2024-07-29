package com.csv.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "csv_data")
@NoArgsConstructor
@Data
public class CSVData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "source")
    private String source;
    @Column(name = "codeListCode")
    private String codeListCode;
    @Column(name = "code")
    private String code;
    @Column(name = "displayValue")
    private String displayValue;
    @Column(name = "longDescription")
    private String longDescription;
    @Column(name ="fromDate")
    private Date fromDate;
    @Column(name = "toDate")
    private Date toDate;
    @Column(name = "sortingPriority")
    private int sortingPriority;

}
