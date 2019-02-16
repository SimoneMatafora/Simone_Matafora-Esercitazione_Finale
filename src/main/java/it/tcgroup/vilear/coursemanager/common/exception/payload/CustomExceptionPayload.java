package it.tcgroup.vilear.coursemanager.common.exception.payload;


import it.tcgroup.vilear.coursemanager.common.exception.AbstractException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionPayload extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Throwable throwable = getError(webRequest);

        if (throwable != null) {
            if (throwable.getCause() != null) {
                Throwable cause = throwable.getCause();
                Map<String, Object> causeErrorAttributes = new HashMap<>();
                causeErrorAttributes.put("exception", cause.getClass().getName());
                causeErrorAttributes.put("message", cause.getMessage());
                errorAttributes.put("details", causeErrorAttributes);
                if (cause instanceof AbstractException) {
                    AbstractException exception = (AbstractException) cause;
                    String code = exception.getCode();
                    if (code != null && !code.isEmpty()) {
                        errorAttributes.put("code", code);
                    }
                }
            }
            if (errorAttributes.get("code") == null && throwable instanceof AbstractException) {
                AbstractException exception = (AbstractException) throwable;
                String code = exception.getCode();
                if (code != null && !code.isEmpty()) {
                    errorAttributes.put("code", code);
                }
            }
        } else {
            errorAttributes.remove("exception");
        }

        return errorAttributes;
    }
}
