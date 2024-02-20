package com.ecom.userservice.dtos;

import com.ecom.userservice.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;
    private Integer age;
    private Long phoneNumber;
    private String email;
    public User getUserFromDTO() {
        User user  = new User();
        user.setName(name);
        user.setAge(age);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        return user;
    }
}
