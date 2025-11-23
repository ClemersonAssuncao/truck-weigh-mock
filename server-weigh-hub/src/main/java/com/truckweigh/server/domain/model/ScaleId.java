package com.truckweigh.server.domain.model;

import java.util.Objects;

public class ScaleId {
    
    private final String value;
    
    public ScaleId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Scale ID cannot be null or empty");
        }
        this.value = value.trim();
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScaleId scaleId = (ScaleId) o;
        return Objects.equals(value, scaleId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}
