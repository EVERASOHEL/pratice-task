package com.demosecurity.utils;

import com.demosecurity.dto.securityDTO.UserDTO;
import com.demosecurity.model.securityModels.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    private String createdBy;
    private LocalDateTime createdAt;
}
