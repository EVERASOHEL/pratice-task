package com.demosecurity.utils;

import com.demosecurity.dto.securityDTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMapper {

    public static UserDTO convertUserDetailsToUserDTO(UserDetails userDetails) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userDetails.getUsername());
        return userDTO;
    }

}
