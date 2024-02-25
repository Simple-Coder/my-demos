package xxd.demos.hystrix;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by xiedong
 * Date: 2024/2/24 22:55
 */
@SpringBootApplication
//@Import({HystrixCircuitBreakerConfiguration.class, JetCacheAutoConfiguration.class})
@EnableHystrix
@EnableMethodCache(basePackages = "xxd.demos.hystrix")
public class MyHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyHystrixApplication.class, args);
    }
}