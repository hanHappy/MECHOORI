package com.mechoori.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Java {

    @Id
    private Long id;


    private String username;
    private String email;


}
