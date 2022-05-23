package com.example.SwapMateWeb.Model;

import com.google.cloud.firestore.DocumentReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String id;
    private String address;
    private Integer age;
    private String contact;
    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    private String nic;
    private String points;
    private String regdate;
    private String size;
    private String cid;


}
