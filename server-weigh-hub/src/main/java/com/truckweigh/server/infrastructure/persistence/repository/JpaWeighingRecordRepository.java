package com.truckweigh.server.infrastructure.persistence.repository;

import com.truckweigh.server.infrastructure.persistence.entity.WeighingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaWeighingRecordRepository extends JpaRepository<WeighingRecordEntity, String> {
    
    List<WeighingRecordEntity> findByScaleId(String scaleId);
    
    List<WeighingRecordEntity> findByTruckPlate(String truckPlate);
}
