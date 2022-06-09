package com.example.applicationdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
                "kafka.receiver",
                "kafka.producer",
        }
)
@EnableDiscoveryClient
public class ApplicationDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDockerApplication.class, args);
    }

}
