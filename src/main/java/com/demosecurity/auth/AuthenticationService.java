package com.demosecurity.auth;

import com.demosecurity.config.JwtService;
import com.demosecurity.dto.permissionDTO.UserRoleModulePermissionDTO;
import com.demosecurity.dto.securityDTO.UserDTO;
import com.demosecurity.model.securityModels.Role;
import com.demosecurity.model.securityModels.User;
import com.demosecurity.repository.securityRepo.RoleRepository;
import com.demosecurity.repository.securityRepo.UserPermissionRepository;
import com.demosecurity.repository.securityRepo.UserRepository;
import com.demosecurity.utils.ApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AuthenticationResponse register(UserDTO request) {

        Set<Role> roles = request.getRoleDTO().stream()
                .map(roleDto -> {
                    Role role = roleRepository.findByName(roleDto.getName());
                    if (role == null) {
                        throw new IllegalArgumentException("Role not found: " + roleDto.getName());
                    }
                    return role;
                })
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
//            Authentication authenticate = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getEmail(),
//                            request.getPassword()
//                    )
//            );
//            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
//            if (!userDetails.isEnabled()) {
//                throw new DisabledException("User is disabled");
//            }
        } catch (UsernameNotFoundException e) {
            return AuthenticationResponse.builder()
                    .message("User not found")
                    .build();
        } catch (DisabledException e) {
            return AuthenticationResponse.builder()
                    .message("User is disabled")
                    .build();
        }
        var user = userepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        final Map<String, Object> permissions = findUserPermissionsByUserId(user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("your are successfully login!")
                .modulePermissions(permissions)
                .build();
    }

    public ApiResponse getAllUsers(){
        List<User> userList = userepository.findAll();
        return ApiResponse.builder()
                .data(userList)
                .message("successfully get All User")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Transactional
    public Map<String, Object> findUserPermissionsByUserId(Long userId) {
        String roleNameQuery = "SELECT tr.name " +
                "FROM tbl_user_permission tup " +
                "LEFT JOIN tbl_role tr ON tup.role_id = tr.id " +
                "WHERE tup.user_id = :userId";

        final Query roleNameQueryString = entityManager.createNativeQuery(roleNameQuery);
        roleNameQueryString.setParameter("userId", Objects.nonNull(userId) ? userId : null);
        List<String> roleNames = roleNameQueryString.getResultList();

        String queryString = "SELECT " +
                "  tup.id, " +
                "  tmp.name AS moduleName, " +
                "  tp.can_write, " +
                "  tp.can_read, " +
                "  tp.can_update, " +
                "  tp.can_delete " +
                "FROM tbl_user_permission tup " +
                "LEFT JOIN tbl_permission tp ON tup.permission_id = tp.id " +
                "LEFT JOIN tbl_role tr ON tup.role_id = tr.id " +
                "LEFT JOIN tbl_module_permission tmp ON tp.module_id = tmp.id " +
                "WHERE tup.user_id = :userId";

        final Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("userId", Objects.nonNull(userId) ? userId : null);

        final List<Object[]> resultList = query.getResultList();
        final List<UserRoleModulePermissionDTO> permissionDTOList = resultList.stream()
                .map(UserRoleModulePermissionDTO::new)
                .collect(Collectors.toList());

        // Group permissions by role name
        Map<String, Object> groupedPermissions = new HashMap<>();
        roleNames.stream().findFirst().ifPresent(roleName -> groupedPermissions.put("role", roleName));
        groupedPermissions.put("modulePermissions", permissionDTOList);

        return groupedPermissions;
    }


}
