package com.core.tcg.acceptance;

import com.core.tcg.driver.*;
import com.core.tcg.driver.Adapter.DriverClass;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AcceptanceTestRoot<Account, Card> {
    protected Driver<Account, Card> testDriver = (Driver<Account, Card>) new DriverClass();
}