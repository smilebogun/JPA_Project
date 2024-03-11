package kr.ezen.yni_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
//@PropertySource("classpath:application.properties")
public class EmailConfig {

    @Value("587")
    private int port;
    @Value("true")
    private boolean auth;
    @Value("true")
    private boolean starttls;
    @Value("smilebogun1@gmail.com")
    private String id;
    @Value("sdsdnzcptlufsnkm")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(port);
        mailSender.setUsername(id);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}

