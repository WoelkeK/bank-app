package pl.woelke.krzysztof.java.spring.app.bank.api.exception.config;

import javax.servlet.http.HttpServletRequest;

public class ErrorInfo {
    private String url;
    private String message;

    public ErrorInfo() {
    }

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public ErrorInfo(HttpServletRequest request, Exception ex) {
        this.url = request.getRequestURL().toString();
        this.message = ex.getMessage();
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
