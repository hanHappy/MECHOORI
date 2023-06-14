package com.mechoori.web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Program {

        public static void main(String[] args) {
            String password = "dlstnehowl1";

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(password);

            System.out.println("Hashed Password: " + hashedPassword);
        }
    }
