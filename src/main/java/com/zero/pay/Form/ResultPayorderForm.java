package com.zero.pay.Form;

import lombok.Data;

@Data
public class ResultPayorderForm {
    private String id;

    private String Type;

    private String amount;

    @Override
    public String toString() {
        return "ResultPayorderForm{" +
                "id='" + id + '\'' +
                ", Type='" + Type + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
