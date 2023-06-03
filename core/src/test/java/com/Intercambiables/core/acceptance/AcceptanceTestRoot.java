package com.Intercambiables.core.acceptance;

import com.Intercambiables.core.driver.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AcceptanceTestRoot<User, Card> {
    protected Driver<User, Card> testDriver = (Driver<User, Card>) new DriverClass();
}