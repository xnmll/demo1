package cn.xnmll.sbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xnmll.sbdemo.mapper")
public class SbdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }

}
