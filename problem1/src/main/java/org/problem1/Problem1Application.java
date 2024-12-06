package org.problem1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.problem1.dao")
@SpringBootApplication
public class Problem1Application {

    public static void main(String[] args) {
        SpringApplication.run(Problem1Application.class, args);
    }

}
