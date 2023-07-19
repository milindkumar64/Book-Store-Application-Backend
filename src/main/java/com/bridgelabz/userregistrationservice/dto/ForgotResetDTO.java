package com.bridgelabz.userregistrationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotResetDTO {

    @NotNull(message = "email Cannot be empty")
    public String email;

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String password;

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String confirmPassword;




}
