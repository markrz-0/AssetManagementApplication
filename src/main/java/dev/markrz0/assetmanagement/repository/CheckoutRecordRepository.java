package dev.markrz0.assetmanagement.repository;

import dev.markrz0.assetmanagement.model.entity.CheckoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRecordRepository extends JpaRepository<CheckoutRecord, Long> {

    List<CheckoutRecord> findAllByReturnDateIsNull();
}