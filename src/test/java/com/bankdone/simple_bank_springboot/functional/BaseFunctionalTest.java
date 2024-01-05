package com.bankdone.simple_bank_springboot.functional;


import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
//        classes = /java/com/bankdone/simple_bank_springboot/SimpleBankSpringbootApplication.class,               //1
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  //2
@ActiveProfiles("test")
public class BaseFunctionalTest {
//    @Rule
//    public WireMockRule contactsServiceMock = new WireMockRule(options().port(8777)); //4
//
//    @Autowired                                                       //5
//    protected UserDetailsServiceSteps userDetailsServiceSteps;
//    @Autowired
//    protected ContactsServiceSteps contactsServiceSteps;
//
//    @TestConfiguration                                               //6
//    @ComponentScan("com.tdanylchuk.user.details.steps")
//    public static class StepsConfiguration {
//    }
}
