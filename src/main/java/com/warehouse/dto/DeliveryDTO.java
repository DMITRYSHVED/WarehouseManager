package com.warehouse.dto;

import com.warehouse.entity.Product;
import com.warehouse.entity.Provider;
import com.warehouse.util.ValidationConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DeliveryDTO {

    @FutureOrPresent(message = ValidationConstants.FUTURE_OR_PRESENT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @NotBlank(message = "выберите поставщика")
    private String providerId;


    public DeliveryDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getProviderId() {
        return Integer.parseInt(providerId);
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryDTO that = (DeliveryDTO) o;
        return providerId == that.providerId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, providerId);
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "date=" + date +
                ", providerId=" + providerId +
                '}';
    }
}
