package pl.woelke.krzysztof.java.spring.app.bank.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalDefaultExceptionHandler.class.getName());

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception ex){
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
//        if (AnnotationUtils.findAnnotation
//                (e.getClass(), ResponseStatus.class) != null)
//            throw e;

        // Otherwise setup and send the user to a default error-view.

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex.getLocalizedMessage());
        mav.addObject("url", ex.getMessage());
        LOGGER.info("mav: " + mav);
        return mav;
    }
}