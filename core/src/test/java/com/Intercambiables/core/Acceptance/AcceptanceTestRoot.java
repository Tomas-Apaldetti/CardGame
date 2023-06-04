package com.Intercambiables.core.Acceptance;

import org.springframework.boot.test.context.SpringBootTest;

import com.Intercambiables.core.GameDriver.*;

@SpringBootTest
public class AcceptanceTestRoot<User, Card> {
    protected Driver<User, Card> testDriver = (Driver<User, Card>) new DriverClass();
}