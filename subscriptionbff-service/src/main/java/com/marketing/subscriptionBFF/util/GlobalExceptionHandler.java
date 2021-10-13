package com.marketing.subscriptionBFF.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public HttpError handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        HttpError error = new HttpError();
        error.setStatus(e.status());

        log.debug("Feign integration error: status {}. path: {}. ", e.status(), e.request().url());
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            error = mapper.readValue(e.contentUTF8(),
                    HttpError.class);
        } catch (JsonProcessingException ex) {
            log.error("Fail to convert. body {} ", e.contentUTF8());
        }
        return error;
    }
}
