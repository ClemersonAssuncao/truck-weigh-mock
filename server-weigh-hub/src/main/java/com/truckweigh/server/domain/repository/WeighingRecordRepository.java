package com.truckweigh.server.domain.repository;

import com.truckweigh.server.domain.model.WeighingRecord;

import java.util.List;

public interface WeighingRecordRepository {
    
    WeighingRecord save(WeighingRecord record);
    
    WeighingRecord findById(String id);
    
    List<WeighingRecord> findAll();
    
    List<WeighingRecord> findByScaleId(String scaleId);
    
    List<WeighingRecord> findByPlate(String plate);
}
