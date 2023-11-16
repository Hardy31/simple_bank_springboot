package com.bankdone.simple_bank_springboot.util;

import lombok.experimental.UtilityClass;
import org.testcontainers.containers.PostgreSQLContainer;

//@UtilityClass
public class HibernateTestUtil {
    public static final PostgreSQLContainer <?> postgres = new PostgreSQLContainer<>("postgres:13");

    static {
        postgres.start();
    }
}
