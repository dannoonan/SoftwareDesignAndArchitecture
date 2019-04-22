package ie.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("ie.demo.mapper")
public class MySpringBootDemo extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootDemo.class, args);
    }
}