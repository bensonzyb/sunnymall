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
 * 绋嬪簭鍚姩鍏ュ彛
 *
 * @ServletComponentScan 璁剧疆鍚姩鏃秙pring鑳藉鎵弿鍒版垜浠嚜宸辩紪鍐欑殑servlet鍜宖ilter, 鐢ㄤ簬Druid鐩戞帶
 * @MapperScan("com.imlaidian.springbootdemo.dao") 鎵弿mybatis Mapper鎺ュ彛
 * @EnableScheduling 鍚敤瀹氭椂浠诲姟
 * @EnableTransactionManagement 寮�鍚簨鍔�
 *
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
