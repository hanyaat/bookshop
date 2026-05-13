package com.example.bookshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "名前は必須です")
    private String name;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message="メールアドレスの形式が正しくありません")
    private String email;

    @NotBlank(message = "パスワードは必須です")
    private String password;

    private String address;

    private String gender;

    private int age;

    private String paymentMethod;

    //--- getter/setter---
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}

    public String getGender(){return gender;}
    public void setGender(String address){this.gender=gender;}

    public int getAge(){return age;}
    public void setAge(int age){this.age=age;}

    public String getPaymentMethod(){return paymentMethod;}
    public void setPaymentMethod(String paymentMethod){this.paymentMethod=paymentMethod;}

     
     



    
}
