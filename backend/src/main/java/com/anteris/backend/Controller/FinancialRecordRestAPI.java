package com.anteris.backend.Controller;

import com.anteris.backend.Message.response.DonationResponse;
import com.anteris.backend.Message.response.FinancialResponse;
import com.anteris.backend.Model.FinancialRecord;
import com.anteris.backend.Service.FinancialRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/financial")
public class FinancialRecordRestAPI {

    @Autowired
    FinancialRecordService financialRecordService;


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('FINANCIALMANAGER', 'ADMIN')")
    public ResponseEntity<List<FinancialRecord>> allRecords() {
        return financialRecordService.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('FINANCIALMANAGER', 'ADMIN')")
    public ResponseEntity<?> newRecord(@RequestBody FinancialRecord financialRecord) {
        return financialRecordService.newRecord(financialRecord);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('FINANCIALMANAGER', 'ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return financialRecordService.delete(id);
    }

}
