package com.example.applicationsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.applicationsoap",
        "com.example.applicationsoap.soapAdapters",
        "com.example.applicationsoap.soapConverters",
        "com.example.applicationsoap.soapEndpoints",
        "com.example.applicationsoap.soapmodel.lanemodel",
        "com.example.applicationsoap.soapmodel.usermodel",
        "com.example.applicationsoap.soapmodel.reservationmodel",
        "service",
        "repository",
        "model",
        "adapters",
        "Port.In",
        "Port.Out",
        "converters",
})
public class ApplicationSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSoapApplication.class, args);
    }

}
