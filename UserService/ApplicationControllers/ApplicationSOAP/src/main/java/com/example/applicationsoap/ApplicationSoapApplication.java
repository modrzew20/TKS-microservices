package com.example.applicationsoap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.applicationsoap",
        "com.example.applicationsoap.soapAdapters",
        "com.example.applicationsoap.soapConverters",
        "com.example.applicationsoap.soapEndpoints",
        "com.example.applicationsoap.soapmodel.usermodel",
        "exceptions",
        "service",
        "repository",
        "model",
        "adapters",
        "Port.In",
        "Port.Out",
        "converters",
        "kafka.producer",
})
public class ApplicationSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSoapApplication.class, args);
    }

}
