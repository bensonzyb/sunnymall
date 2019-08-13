package priv.jesse.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序启动入口
 *
 * @ServletComponentScan 设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
 * @MapperScan("com.imlaidian.springbootdemo.dao") 扫描mybatis Mapper接口
 * @EnableScheduling 启用定时任务
 * @EnableTransactionManagement 开启事务
 * @author hfb
 * @date 2019/2/18 11:13
 */
@ServletComponentScan
@EnableConfigurationProperties
@EnableTransactionManagement
@SpringBootApplication
public class MallApplication  extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}
	
	
	//用于构建war文件并进行部署
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MallApplication.class);
    }

}
