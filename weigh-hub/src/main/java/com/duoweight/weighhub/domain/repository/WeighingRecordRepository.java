package com.duoweight.weighhub.domain.repository;

import com.duoweight.weighhub.domain.model.WeighingRecord;

import java.util.List;

public interface WeighingRecordRepository {
    
    WeighingRecord save(WeighingRecord record);
    
    WeighingRecord findById(String id);
    
    List<WeighingRecord> findAll();
    
    List<WeighingRecord> findByScaleId(String scaleId);
    
    List<WeighingRecord> findByPlate(String plate);
}
