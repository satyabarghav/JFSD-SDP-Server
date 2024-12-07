package com.klef.jfsd.sdp.service;


import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public interface CSVService {
    
    List<CSVRecord> parseCsv(MultipartFile file, String[] headers) throws IOException;

   
    <T> List<T> parseCsv(MultipartFile file, String[] headers, RecordMapper<T> mapper) throws IOException;

    @FunctionalInterface
    interface RecordMapper<T> {
        T map(CSVRecord record);
    }
}
