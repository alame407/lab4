package com.alame.lab4.controller;

import com.alame.lab4.config.JwtTokenUtil;
import com.alame.lab4.model.*;
import com.alame.lab4.repository.RoleRepository;
import com.alame.lab4.repository.UserRepository;
import com.alame.lab4.service.NewUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@CrossOrigin(originPatterns = "http://localhost:29381")
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final NewUserService newUserService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, NewUserService newUserService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.newUserService = newUserService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>(new JwtAuthResponse(false, "username already taken", "", "", ""),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role roles = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        String jwt = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtAuthResponse(true, "", "register successfully", jwt, user.getUsername()));
    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse
            response){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        UserDetails userDetails = newUserService.loadUserByUsername(loginDTO.getUsername());
        String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthResponse(true, "", "login successfully", jwt, userDetails.getUsername()));
    }
}
