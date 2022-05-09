package com.doughlima.wishlist.bdd.infraestructure;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@CucumberContextConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpringContextConfiguration {

}
