package com.demosecurity.dto.securityDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RestError {

    private String status;
    private String message;

    public RestError(String status, String authentication_failed_message) {
        this.status=status;
        this.message=authentication_failed_message;
    }
}
