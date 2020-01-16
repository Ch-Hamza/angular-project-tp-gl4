package com.anteris.backend.Repository;
import com.anteris.backend.Model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRecordRepository  extends JpaRepository<FinancialRecord, Long> {

}
