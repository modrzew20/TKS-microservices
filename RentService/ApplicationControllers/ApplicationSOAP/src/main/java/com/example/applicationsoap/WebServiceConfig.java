package com.example.applicationsoap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "lanemodel")
    public DefaultWsdl11Definition laneWsdl11Definition(XsdSchema laneSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Lane");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/applicationsoap/soapmodel/lanemodel");
        wsdl11Definition.setSchema(laneSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema laneSchema() {
        return new SimpleXsdSchema(new ClassPathResource("lanes.xsd"));
    }

    @Bean(name = "usermodel")
    public DefaultWsdl11Definition userWsdl11Definition(XsdSchema userSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("User");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/applicationsoap/soapmodel/usermodel");
        wsdl11Definition.setSchema(userSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("user.xsd"));
    }

    @Bean(name = "reservationmodel")
    public DefaultWsdl11Definition reservationWsdl11Definition(XsdSchema reservationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Reservation");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/applicationsoap/soapmodel/reservationmodel");
        wsdl11Definition.setSchema(reservationSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema reservationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("reservation.xsd"));
    }




}
