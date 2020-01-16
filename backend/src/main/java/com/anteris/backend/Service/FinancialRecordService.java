package com.anteris.backend.Service;

import com.anteris.backend.Message.response.FinancialResponse;
import com.anteris.backend.Message.response.ResponseMessage;
import com.anteris.backend.Model.FinancialRecord;
import com.anteris.backend.Repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialRecordService {
    @Autowired
    FinancialRecordRepository financialRecordRepository;

    public ResponseEntity<List<FinancialRecord>> findAll() {
        try {
            List<FinancialRecord> records = financialRecordRepository.findAll();
            if(records.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(records, HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> newRecord(FinancialRecord financialRecord) {
        financialRecordRepository.save(financialRecord);
        return new ResponseEntity<>(financialRecord, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(Long id) {

        Optional<FinancialRecord> financialRecord = financialRecordRepository.findById(id);
        if(financialRecord.isPresent()){
            financialRecordRepository.delete(financialRecord.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
