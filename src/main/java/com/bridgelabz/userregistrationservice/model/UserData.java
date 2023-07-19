package com.bridgelabz.userregistrationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Table(name = "UserData")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserData {

   @Id
   @GeneratedValue
   private long userId;
   private String firstName;
   private String lastName;
   private LocalDate dob;
   private LocalDate registeredDate;
   private LocalDate updatedDate;
   private String email;
   private String password;
   private int otp;
   private boolean verify;

   public void updatedDate(UserDTO userDTO) {
      this.firstName = userDTO.getFirstName();
      this.lastName = userDTO.getLastName();
      this.dob = userDTO.getDob();
      this.registeredDate = LocalDate.now();
      this.email = userDTO.getEmail();
      this.password = userDTO.getPassword();
   }
   public UserData(UserDTO userDTO) {
      this.updatedDate(userDTO);
   }

}
