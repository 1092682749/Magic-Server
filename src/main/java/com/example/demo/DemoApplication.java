package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.websocket.server.WsSci;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
@ServletComponentScan
@MapperScan("com.example.demo.dao")
public class DemoApplication {
    @Value("${server.port}")
    Integer serverPort;
    @Value("${proxy.port}")
    Integer proxyPort;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    /**
     * 配置一个TomcatEmbeddedServletContainerFactory bean
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
     * 但是不能同时在application.properties中同时配置两个connector，
     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
     * @return Connector
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(proxyPort); // http端口
        connector.setSecure(false);
        connector.setRedirectPort(serverPort); // application.properties中配置的https端口
        return connector;
    }

    /**
     * 创建wss协议接口
     * @return
     */
//    @Bean
//    public TomcatContextCustomizer tomcatContextCustomizer() {
//        System.out.println("TOMCATCONTEXTCUSTOMIZER INITILIZED");
//        return new TomcatContextCustomizer() {
//            @Override
//            public void customize(Context context) {
//                context.addServletContainerInitializer(new WsSci(), null);
//            }
//
//        };
//    }

}
