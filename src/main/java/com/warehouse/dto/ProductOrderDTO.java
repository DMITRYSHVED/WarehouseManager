package com.warehouse.dto;

import com.warehouse.util.ValidationConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class ProductOrderDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = ValidationConstants.FUTURE_OR_PRESENT)
    private Date date;

    @NotBlank(message = "укажите имя")
    @Size(max = 250)
    private String clientName;

    @NotBlank(message = "укажите контакт")
    @Size(max = 250)
    private String clientContact;

    @NotBlank(message = "укажите адресс")
    @Size(max = 250)
    private String clientAddress;

    public ProductOrderDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderDTO that = (ProductOrderDTO) o;
        return Objects.equals(date, that.date) && Objects.equals(clientName, that.clientName) && Objects.equals(clientContact, that.clientContact) && Objects.equals(clientAddress, that.clientAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, clientName, clientContact, clientAddress);
    }

    @Override
    public String toString() {
        return "ProductOrderDTO{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", clientContact='" + clientContact + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                '}';
    }
}
