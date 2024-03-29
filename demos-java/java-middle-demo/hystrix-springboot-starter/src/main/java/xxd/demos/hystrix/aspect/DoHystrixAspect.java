package xxd.demos.hystrix.aspect;

import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xxd.demos.hystrix.annotation.DoHystrix;
import xxd.demos.hystrix.command.DoHystrixCommand;
import xxd.demos.hystrix.service.HystrixCacheService;

import java.lang.reflect.Method;

/**
 * Created by xiedong
 * Date: 2024/2/24 22:39
 */
@Aspect
public class DoHystrixAspect {
    private final HystrixCacheService hystrixCacheService;

    public DoHystrixAspect(HystrixCacheService hystrixCacheService) {
        this.hystrixCacheService = hystrixCacheService;
    }

    @Pointcut("@annotation(xxd.demos.hystrix.annotation.DoHystrix)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint jp, DoHystrix doGovern) throws Throwable {
        DoHystrixCommand doHystrixCommand = new DoHystrixCommand(doGovern, hystrixCacheService);
        return doHystrixCommand.access(jp, getMethod(jp), jp.getArgs());
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
