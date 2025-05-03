package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.User.request.UserRequestDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.RefreshToken;
import org.codewhiskers.vetapp.entity.Role;
import org.codewhiskers.vetapp.entity.Specialization;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.enums.RoleType;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.jwt.AuthRequest;
import org.codewhiskers.vetapp.jwt.AuthResponse;
import org.codewhiskers.vetapp.jwt.JwtService;
import org.codewhiskers.vetapp.jwt.RefreshTokenRequest;
import org.codewhiskers.vetapp.mapper.UserMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.RoleRepository;
import org.codewhiskers.vetapp.repository.SpecializationRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.codewhiskers.vetapp.service.impl.RefreshTokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RestAuthController {

    private final AuthenticationManager authenticationManager;
    private final SpecializationRepository specializationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final RoleRepository roleRepository;
    private final ClinicRepository clinicRepository;

    private Specialization findSpecializationById(Long id) {
        return specializationRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    private Clinic findClinicById(Long id) {
        return clinicRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getClinicId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, "Klinik ID boş olamaz."));
        }
        
        if (userRequestDTO.getSpecializationId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, "Uzmanlık ID boş olamaz."));
        }

        Clinic clinic = findClinicById(userRequestDTO.getClinicId());
        Specialization specialization = findSpecializationById(userRequestDTO.getSpecializationId());
        
        User user = userMapper.requestDTOToUser(userRequestDTO, clinic);
        user.setSpecialization(specialization);

        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,""));
        }

        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // 🌟 Varsayılan olarak ROLE_RECEPTIONIST ata
        Role defaultRole = roleRepository.findByName(RoleType.ROLE_RECEPTIONIST)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "role: ROLE_RECEPTIONIST")
                ));
        user.setRoles(Set.of(defaultRole));

        userRepository.save(user);
        return ResponseEntity.ok("Kayıt başarılı");
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "username: " + request.getUsername())
                ));

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.ok(new AuthResponse(
            jwtToken, 
            refreshToken.getRefreshToken(), 
            user.getClinic().getId(),
            user.getSpecialization() != null ? user.getSpecialization().getId() : null
        ));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshTokenStr = request.getRefreshToken();
        RefreshToken token = refreshTokenService.findByToken(refreshTokenStr);
        refreshTokenService.verifyExpiration(token);
        User user = token.getUser();

        String newAccessToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(
            newAccessToken, 
            refreshTokenStr, 
            user.getClinic().getId(),
            user.getSpecialization() != null ? user.getSpecialization().getId() : null
        ));
    }
}
