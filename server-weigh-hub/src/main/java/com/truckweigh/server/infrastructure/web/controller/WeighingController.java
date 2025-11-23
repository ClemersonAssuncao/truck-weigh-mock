package com.truckweigh.server.infrastructure.web.controller;

import com.truckweigh.server.application.dto.RecordWeighingRequest;
import com.truckweigh.server.application.dto.RecordWeighingResponse;
import com.truckweigh.server.application.usecase.GetWeighingRecordsUseCase;
import com.truckweigh.server.application.usecase.RecordWeighingUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weighing")
public class WeighingController {
    
    private final RecordWeighingUseCase recordWeighingUseCase;
    private final GetWeighingRecordsUseCase getWeighingRecordsUseCase;
    
    public WeighingController(RecordWeighingUseCase recordWeighingUseCase, 
                             GetWeighingRecordsUseCase getWeighingRecordsUseCase) {
        this.recordWeighingUseCase = recordWeighingUseCase;
        this.getWeighingRecordsUseCase = getWeighingRecordsUseCase;
    }
    
    @PostMapping("/records")
    public ResponseEntity<RecordWeighingResponse> recordWeighing(@Valid @RequestBody RecordWeighingRequest request) {
        RecordWeighingResponse response = recordWeighingUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/records")
    public ResponseEntity<List<RecordWeighingResponse>> getAllRecords(
            @RequestParam(required = false) String scaleId,
            @RequestParam(required = false) String plate) {
        
        List<RecordWeighingResponse> records;
        
        if (scaleId != null) {
            records = getWeighingRecordsUseCase.getRecordsByScaleId(scaleId);
        } else if (plate != null) {
            records = getWeighingRecordsUseCase.getRecordsByPlate(plate);
        } else {
            records = getWeighingRecordsUseCase.getAllRecords();
        }
        
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/records/{id}")
    public ResponseEntity<RecordWeighingResponse> getRecordById(@PathVariable String id) {
        RecordWeighingResponse response = getWeighingRecordsUseCase.getRecordById(id);
        
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(response);
    }
}
