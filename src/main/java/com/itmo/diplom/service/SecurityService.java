package com.itmo.diplom.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
