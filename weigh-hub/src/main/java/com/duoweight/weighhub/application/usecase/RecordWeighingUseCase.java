package com.duoweight.weighhub.application.usecase;

import com.duoweight.weighhub.application.dto.RecordWeighingRequest;
import com.duoweight.weighhub.application.dto.RecordWeighingResponse;
import com.duoweight.weighhub.domain.model.ScaleId;
import com.duoweight.weighhub.domain.model.TruckPlate;
import com.duoweight.weighhub.domain.model.Weight;
import com.duoweight.weighhub.domain.model.WeighingRecord;
import com.duoweight.weighhub.domain.repository.WeighingRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordWeighingUseCase {
    
    private final WeighingRecordRepository repository;
    
    public RecordWeighingUseCase(WeighingRecordRepository repository) {
        this.repository = repository;
    }
    
    public RecordWeighingResponse execute(RecordWeighingRequest request) {
        // Create value objects from request
        ScaleId scaleId = new ScaleId(request.getScaleId());
        TruckPlate plate = new TruckPlate(request.getPlate());
        Weight weight = new Weight(request.getWeight());
        
        // Create domain entity
        WeighingRecord record = new WeighingRecord(scaleId, plate, weight);
        
        // Persist
        WeighingRecord savedRecord = repository.save(record);
        
        // Map to response
        return new RecordWeighingResponse(
            savedRecord.getId(),
            savedRecord.getScaleId().getValue(),
            savedRecord.getPlate().getValue(),
            savedRecord.getWeight().getValue(),
            savedRecord.getTimestamp()
        );
    }
}
