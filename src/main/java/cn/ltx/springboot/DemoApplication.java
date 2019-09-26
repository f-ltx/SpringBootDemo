package cn.ltx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 启动类
 * 其中：@MapperScan是配置mybatis对应的mapper类的路径
 *
 * @author litianxiang
 * @date 2019/1/24
 */
@SpringBootApplication
@MapperScan("cn.ltx.springboot.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    /**
//     * 使用@Bean注解注入第三方的解析框架（fastJson）
//     */
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        //1、首先需要先定义一个convert转换消息对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //2、添加fastJson的配置信息，比如：是否要格式化返回的json数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //3、在convert中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//
//        return new HttpMessageConverters(fastConverter);
//    }
}

