package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorMapper implements ErrorDecoder {
	
    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new StudentNotFoundException("Student not found");
        }
        return defaultDecoder.decode(methodKey, response);
    }

}
