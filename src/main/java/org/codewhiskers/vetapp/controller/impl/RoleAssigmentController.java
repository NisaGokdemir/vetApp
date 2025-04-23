package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.entity.Role;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.enums.RoleType;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.repository.RoleRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role-assigment")
@RequiredArgsConstructor
public class RoleAssigmentController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/assign-role")
    public ResponseEntity<String> assignRoleToUser(@RequestParam String username, @RequestParam RoleType roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, "User not found: " + username)
        ));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Role not found: " + username)
                ));
        user.getRoles().add(role);
        userRepository.save(user);

        return ResponseEntity.ok("Rol atandı.");
    }
}
