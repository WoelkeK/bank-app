package pl.woelke.krzysztof.java.spring.app.bank.api.exception;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String message) {
        super(message);
    }

//    public CurrencyNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }

}
