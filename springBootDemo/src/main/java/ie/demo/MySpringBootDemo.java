package ie.demo;

import ie.demo.inventorymanagement.Framework;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("ie.demo.mapper")
public class MySpringBootDemo extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Framework.getInstance(); // Create instance of interception framework to be used throughout
        SpringApplication.run(MySpringBootDemo.class, args);
    }
}