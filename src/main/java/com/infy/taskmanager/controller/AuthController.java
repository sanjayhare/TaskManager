package com.infy.taskmanager.controller;
import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.dto.AuthenticateRequestDto;
import com.infy.taskmanager.dto.AuthenticateResponseDto;
import com.infy.taskmanager.dto.ResponseDto;
import com.infy.taskmanager.entity.Users;
import com.infy.taskmanager.security.UserAuthentication;
import com.infy.taskmanager.security.UserAuthenticationManager;
import com.infy.taskmanager.service.impl.UserServiceImpl;
import com.infy.taskmanager.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
   private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody Users user) {
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(TaskConstants.STATUS_201, TaskConstants.MESSAGE_201));
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticationPage(@RequestBody AuthenticateRequestDto authenticateRequestDto){
        System.out.println("/authenticate");
        authenticate(authenticateRequestDto);
        UserDetails securityUser = null;
        try
        {
            securityUser =  userService.loadUserByUsername(authenticateRequestDto.getUsername());
        }
        catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
        }
        String jwtToken = jwtUtil.generateToken(securityUser.getUsername());
        return ResponseEntity.ok().body(new AuthenticateResponseDto(jwtToken));
    }

    public void authenticate(AuthenticateRequestDto authenticateRequestDto){
        UserAuthenticationManager userAuthenticationManager =
                new UserAuthenticationManager(authenticateRequestDto.getUsername(),
                        authenticateRequestDto.getPassword(), userService, passwordEncoder );
        try
        {
            userAuthenticationManager.authenticate(new UserAuthentication());
        }catch (DisabledException e)
        {
            e.printStackTrace();
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/getPage")
    public String createUser()
    {
        return "This is public page";
    }
}
