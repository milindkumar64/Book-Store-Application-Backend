package com.bridgelabz.userregistrationservice.service;


import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.model.UserDTO;

public interface IUserService {

    public ResponseDTO registerUser(UserDTO userDTO);

    public String verifyAccount(String email, int otp);

    public String loginUser(String email,String password);

    public String getForgottenPassword(String email);

    public String resetPassword(String email, String password, String confirmPassword);

    ResponseDTO updateByEmail(UserDTO userDTO);

    public ResponseDTO getUserByEmailId(String email);

    public String deleteUserByEmailId(String email);

    public String verifyUsingToken(String token);

}
