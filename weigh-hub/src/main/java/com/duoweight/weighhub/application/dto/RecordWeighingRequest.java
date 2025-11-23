package com.duoweight.weighhub.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class RecordWeighingRequest {
    
    @NotBlank(message = "Scale ID is required")
    private String scaleId;
    
    @NotBlank(message = "Truck plate is required")
    private String plate;
    
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private BigDecimal weight;
    
    public RecordWeighingRequest() {
    }
    
    public RecordWeighingRequest(String scaleId, String plate, BigDecimal weight) {
        this.scaleId = scaleId;
        this.plate = plate;
        this.weight = weight;
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
}
