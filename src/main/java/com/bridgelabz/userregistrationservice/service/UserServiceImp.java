package com.bridgelabz.userregistrationservice.service;

import com.bridgelabz.userregistrationservice.dto.ResponseDTO;
import com.bridgelabz.userregistrationservice.exception.UserNotFoundException;
import com.bridgelabz.userregistrationservice.model.UserDTO;
import com.bridgelabz.userregistrationservice.model.UserData;
import com.bridgelabz.userregistrationservice.repository.UserRepository;
import com.bridgelabz.userregistrationservice.utility.EmailServices;
import com.bridgelabz.userregistrationservice.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class UserServiceImp implements IUserService {


    @Autowired
    EmailServices emailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility jwtToken;

    private UserData checkIfUserExist(String email){
        return userRepository.getUserIDByEmail(email);
    }


    @Override
    public ResponseDTO registerUser(UserDTO userDTO) {

        UserData data = checkIfUserExist(userDTO.getEmail());

        if(data == null){
            Random random = new Random();
            UserData registerNewUser = new UserData(userDTO);
            registerNewUser.setOtp((random.nextInt(9000)+999));
            userRepository.save(registerNewUser);
            emailService.sendEmail(userDTO.getEmail(),"User DataBase Account Verification",
                    "Hey... " + (userDTO.getFirstName()) +
                            "\n\n Your OTP for VERIFICATION :: "+registerNewUser.getOtp()+
                            "\n\n Click link to verify your account :: http://localhost:8014/user-list/verify");
            return new ResponseDTO("User_Information",registerNewUser);
        }
        return new ResponseDTO("User with same Email ID already exist..", data);
    }


    @Override
    public String verifyAccount(String email, int otp) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            System.out.println("verify service");
            if(data.getOtp() == otp){
                data.setVerify(true);
                userRepository.save(data);
                emailService.sendEmail(email,"Account verified successful",
                        "Hello Sir/Mam, \n\n Your Account is successfully verified !!! ");
                return "Account Verified successfully";
            }
        }
        return " User not found with email id :: " + email;
    }

    @Override
    public String loginUser(String email, String password) {

        UserData data = checkIfUserExist(email);
        if(null != data){
            if(data.getPassword().equals(password)){
                String token = jwtToken.createToken(data.getUserId());
                return ":: Welcome " + data.getFirstName() + " " + data.getLastName() + " ::" + "\n\n" + token;
            }
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!";
    }



    public String getForgottenPassword(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data) {
            emailService.sendEmail(email,"Create New Password",
                    "Hello Sir/Mam, \n\n Create new password from the link :: http://localhost:8080/user-list/reset" );
            return "Create new password from your registered email id. A link has been sent :: http://localhost:8080/user-list/reset";
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }

    @Override
    public String resetPassword(String email, String password, String confirmPassword) {
        System.out.println("Password is being reset");
        if(password.equals(confirmPassword)){
            UserData data = checkIfUserExist(email);
            if(null != data) {
                data.setPassword(confirmPassword);
                userRepository.save(data);
                return " Your Password Updated Successfully !!";
            }
            return "User with Email ID : " + email + " doesn't exist..";
        }
        return "Entered Password and Confirm Password doesn't match..";
    }

    @Override
    public ResponseDTO updateByEmail(UserDTO userDTO) {
        UserData data = checkIfUserExist(userDTO.getEmail());
        if(null != data){
            data.updatedDate(userDTO);
            data.setUpdatedDate(LocalDate.now());
            userRepository.save(data);
            return new ResponseDTO("User Details Updated Successfully....", data);
        }
        return new ResponseDTO("!!!!!!!!!!!! User with Email ID :: " + userDTO.getEmail() + " doesn't exists !!!!!!!!!!!!!!", null);
    }
    @Override
    public ResponseDTO getUserByEmailId(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            return new ResponseDTO("Details :: ", data);
        }
        return new ResponseDTO("!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!", null);
    }

    @Override
    public String deleteUserByEmailId(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            userRepository.deleteById(data.getUserId());
            return "User with Email ID :: " + email + " successfully deleted..";
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }

    @Override
    public String verifyUsingToken(String token) {
        long id = jwtToken.decodeToken(token);
        UserData data = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(" User not found with token :: " + token));;
        if(data.isVerify()){
            return " Data is already verified..";
        }
        data.setVerify(true);
        userRepository.save(data);
        return " Data verified Successfully..";
    }


}
