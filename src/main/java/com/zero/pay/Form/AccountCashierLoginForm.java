package com.zero.pay.Form;

import lombok.Data;

import java.util.Objects;

@Data
public class AccountCashierLoginForm {

    private String loginName;

    private String password;


    @Override
    public String toString() {
        return "AccountCashierLoginForm{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCashierLoginForm that = (AccountCashierLoginForm) o;
        return loginName.equals(that.loginName) &&
                password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, password);
    }
}
