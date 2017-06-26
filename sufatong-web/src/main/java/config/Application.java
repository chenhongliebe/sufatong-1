package config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/5
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages="com.legalups.sufatong")
@ImportResource(locations={"classpath:spring/*"})
@MapperScan(basePackages = "com.legalups.sufatong.dao.mapper", annotationClass = javax.annotation.Resource.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
