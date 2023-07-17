package com.mechoori.web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class program {

        public static void main(String[] args) {
            String password = "111";

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(password);

            System.out.println("Hashed Password: " + hashedPassword);
        }
    }