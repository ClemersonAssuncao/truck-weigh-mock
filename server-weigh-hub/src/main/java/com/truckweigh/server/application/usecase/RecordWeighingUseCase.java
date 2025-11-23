package com.truckweigh.server.application.usecase;

import com.truckweigh.server.application.dto.RecordWeighingRequest;
import com.truckweigh.server.application.dto.RecordWeighingResponse;
import com.truckweigh.server.domain.model.ScaleId;
import com.truckweigh.server.domain.model.TruckPlate;
import com.truckweigh.server.domain.model.Weight;
import com.truckweigh.server.domain.model.WeighingRecord;
import com.truckweigh.server.domain.repository.WeighingRecordRepository;
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
