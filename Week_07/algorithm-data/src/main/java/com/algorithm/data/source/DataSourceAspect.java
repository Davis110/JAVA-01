package com.algorithm.data.source;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wei.huang
 * @version Id: DataSourceAspect.java, v 0.1 2021年04月20日  14:45 wei.huang Exp $
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.algorithm.data.source.ReadOnly)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        ReadOnly readOnly = method.getAnnotation(ReadOnly.class);
        if (readOnly == null) {
            DynamicDataSource.setDataSource("master");
        }
        String dataName = readOnly.name();
        if (dataName == null || dataName.equals("") || dataName.equals(DynamicDataSource.masterName)) {
            DynamicDataSource.setDataSource("master");
        } else {
            // 从节点负载均衡
            DynamicDataSource.setDataSource(DynamicDataSource.slavesLoadbance());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
