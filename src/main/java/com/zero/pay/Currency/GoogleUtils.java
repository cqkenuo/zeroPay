package com.zero.pay.Currency;

public class GoogleUtils {

    public static Boolean verifyCode(String secret, Long code) {
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        long t = System.currentTimeMillis();
        boolean b = ga.check_code(secret, code, t);
        return b == true ? true : false;
    }
}
