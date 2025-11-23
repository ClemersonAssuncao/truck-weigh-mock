package com.truckweigh.server.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecordWeighingResponse {
    
    private String recordId;
    private String scaleId;
    private String plate;
    private BigDecimal weight;
    private LocalDateTime timestamp;
    
    public RecordWeighingResponse() {
    }
    
    public RecordWeighingResponse(String recordId, String scaleId, String plate, BigDecimal weight, LocalDateTime timestamp) {
        this.recordId = recordId;
        this.scaleId = scaleId;
        this.plate = plate;
        this.weight = weight;
        this.timestamp = timestamp;
    }
    
    public String getRecordId() {
        return recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    public String getScaleId() {
        return scaleId;
    }
    
    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }
    
    public String getPlate() {
        return plate;
    }
    
    public void setPlate(String plate) {
        this.plate = plate;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
