package com.truckweigh.server.infrastructure.persistence.repository;

import com.truckweigh.server.domain.model.ScaleId;
import com.truckweigh.server.domain.model.TruckPlate;
import com.truckweigh.server.domain.model.Weight;
import com.truckweigh.server.domain.model.WeighingRecord;
import com.truckweigh.server.domain.repository.WeighingRecordRepository;
import com.truckweigh.server.infrastructure.persistence.entity.WeighingRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class WeighingRecordRepositoryImpl implements WeighingRecordRepository {
    
    private final JpaWeighingRecordRepository jpaRepository;
    
    public WeighingRecordRepositoryImpl(JpaWeighingRecordRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public WeighingRecord save(WeighingRecord record) {
        WeighingRecordEntity entity = toEntity(record);
        WeighingRecordEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public WeighingRecord findById(String id) {
        return jpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }
    
    @Override
    public java.util.List<WeighingRecord> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
    
    @Override
    public java.util.List<WeighingRecord> findByScaleId(String scaleId) {
        return jpaRepository.findByScaleId(scaleId).stream()
                .map(this::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
    
    @Override
    public java.util.List<WeighingRecord> findByPlate(String plate) {
        return jpaRepository.findByTruckPlate(plate.toUpperCase()).stream()
                .map(this::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }
    
    private WeighingRecordEntity toEntity(WeighingRecord record) {
        return new WeighingRecordEntity(
                record.getId(),
                record.getScaleId().getValue(),
                record.getPlate().getValue(),
                record.getWeight().getValue(),
                record.getTimestamp()
        );
    }
    
    private WeighingRecord toDomain(WeighingRecordEntity entity) {
        return new WeighingRecord(
                entity.getId(),
                new ScaleId(entity.getScaleId()),
                new TruckPlate(entity.getTruckPlate()),
                new Weight(entity.getWeight()),
                entity.getTimestamp()
        );
    }
}
