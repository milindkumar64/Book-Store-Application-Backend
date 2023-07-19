package com.bridgelabz.userregistrationservice.controller;

import com.bridgelabz.userregistrationservice.dto.ForgotResetDTO;
import com.bridgelabz.userregistrationservice.dto.LoginDTO;
import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.dto.VerifyDTO;
import com.bridgelabz.userregistrationservice.model.UserDTO;
import com.bridgelabz.userregistrationservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-list")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Registered Successfully..", iUserService.registerUser(userDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerifyDTO verifyDTO) {
        return new ResponseEntity<String>(iUserService.verifyAccount(verifyDTO.email, verifyDTO.otp), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<String>(iUserService.loginUser(loginDTO.getEmail(), loginDTO.getPassword()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/forgot")
    public String forgotPassword(@RequestParam String email){
        return iUserService.getForgottenPassword(email);
    }

    @PutMapping("/reset")
    public String resetPassword(@RequestBody ForgotResetDTO forgotResetDTO){
        return iUserService.resetPassword(forgotResetDTO.getEmail(), forgotResetDTO.getPassword(), forgotResetDTO.getConfirmPassword());
    }
    @PutMapping("/updateUser")
    public ResponseEntity<ResponseDTO> updateByEmail(@RequestBody UserDTO userDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Details Updated Successfully..", iUserService.updateByEmail(userDTO)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@RequestParam String email){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(":: User Detail :: ", iUserService.getUserByEmailId(email)), HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserByEmailId(@RequestBody LoginDTO loginDTO){
        return iUserService.deleteUserByEmailId(loginDTO.email);
    }

    @PutMapping("/verifyToken/{token}")
    public String verifyUsingToken(@PathVariable String token){
        return iUserService.verifyUsingToken(token);
    }

}