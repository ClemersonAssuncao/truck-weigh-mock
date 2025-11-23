package com.truckweigh.server.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class TruckPlate {
    
    // Padrão antigo: ABC1234 (3 letras + 4 números)
    private static final Pattern OLD_PATTERN = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
    
    // Padrão Mercosul: ABC1D23 (3 letras + 1 número + 1 letra + 2 números)
    private static final Pattern MERCOSUL_PATTERN = Pattern.compile("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
    
    private final String value;
    
    public TruckPlate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Truck plate cannot be null or empty");
        }
        
        String normalized = value.trim().toUpperCase().replace("-", "");
        
        if (!isValidBrazilianPlate(normalized)) {
            throw new IllegalArgumentException(
                "Invalid Brazilian plate format. Expected old format (ABC1234) or Mercosul format (ABC1D23)"
            );
        }
        
        this.value = normalized;
    }
    
    private boolean isValidBrazilianPlate(String plate) {
        return OLD_PATTERN.matcher(plate).matches() || 
               MERCOSUL_PATTERN.matcher(plate).matches();
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean isMercosulFormat() {
        return MERCOSUL_PATTERN.matcher(value).matches();
    }
    
    public boolean isOldFormat() {
        return OLD_PATTERN.matcher(value).matches();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckPlate that = (TruckPlate) o;
        return Objects.equals(value, that.value);
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
