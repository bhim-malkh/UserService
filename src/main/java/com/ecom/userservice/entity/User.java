package com.ecom.userservice.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseEntity{
    private String name;
    private Integer age;
    private Long phoneNumber;
    private String email;
}
