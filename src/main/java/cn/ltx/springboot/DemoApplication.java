package cn.ltx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Description: 启动类
 * 其中：@MapperScan是配置mybatis对应的mapper类的路径
 * @author      litianxiang
 * @date        2019/1/24
 */
@SpringBootApplication
@MapperScan("cn.ltx.springboot.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

