package com.example.applicationdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "applicationcontroller.api",
                "applicationcontroller.adapters",
                "applicationcontroller.converters",
                "applicationcontroller.modelRest.modelView",
                "com.example.applicationsoap",
                "com.example.applicationsoap.soapAdapters",
                "com.example.applicationsoap.soapConverters",
                "com.example.applicationsoap.soapEndpoints",
                "com.example.applicationsoap.soapmodel.*",
                "service",
                "repository",
                "model",
                "adapters",
                "Port.In",
                "Port.Out",
                "converters",
        }
)
public class ApplicationDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDockerApplication.class, args);
    }

}
