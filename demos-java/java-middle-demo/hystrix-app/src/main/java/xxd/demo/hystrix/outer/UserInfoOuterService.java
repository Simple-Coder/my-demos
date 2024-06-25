package xxd.demo.hystrix.outer;

import io.github.xxee.hystrix.cache.annotation.HystrixCmd;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xiedong
 * 2024/2/27
 */
@Service
@Slf4j
public class UserInfoOuterService {
    @Resource
    private RestTemplate restTemplate;

    @HystrixCmd(
            groupKey = "getNicknameGroup",
            commandKey = "getNickname",
            threadPoolKey = "getNicknameThreadPool",
//            cacheKey = "#userId", useCacheFirst = true)
            cacheKey = "#userId", useCacheAfter = true)
    public String getNickname(long userId) {
        String result = restTemplate.getForObject("http://localhost:8090/outer/user/api/nickname/query?userId=" + userId, String.class);
//        String result = restTemplate.getForObject("http://localhost:" + serverPort + "/outer/user/api/nickname/query?userId=" + userId, String.class);
        return result;
    }

}
