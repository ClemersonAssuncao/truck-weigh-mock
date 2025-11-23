package com.truckweigh.server.application.usecase;

import com.truckweigh.server.application.dto.RecordWeighingResponse;
import com.truckweigh.server.domain.model.WeighingRecord;
import com.truckweigh.server.domain.repository.WeighingRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetWeighingRecordsUseCase {
    
    private final WeighingRecordRepository repository;
    
    public GetWeighingRecordsUseCase(WeighingRecordRepository repository) {
        this.repository = repository;
    }
    
    public List<RecordWeighingResponse> getAllRecords() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public RecordWeighingResponse getRecordById(String id) {
        WeighingRecord record = repository.findById(id);
        return record != null ? toResponse(record) : null;
    }
    
    public List<RecordWeighingResponse> getRecordsByScaleId(String scaleId) {
        return repository.findByScaleId(scaleId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<RecordWeighingResponse> getRecordsByPlate(String plate) {
        return repository.findByPlate(plate).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    private RecordWeighingResponse toResponse(WeighingRecord record) {
        return new RecordWeighingResponse(
                record.getId(),
                record.getScaleId().getValue(),
                record.getPlate().getValue(),
                record.getWeight().getValue(),
                record.getTimestamp()
        );
    }
}
