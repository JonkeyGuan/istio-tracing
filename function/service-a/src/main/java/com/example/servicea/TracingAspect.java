package com.example.servicea;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.opentracing.Span;
import io.opentracing.Tracer;

@Aspect
@Component
public class TracingAspect {
    @Autowired
    Tracer tracer;

    @Around("@annotation(com.example.servicea.Tracing)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String class_name = proceedingJoinPoint.getTarget().getClass().getName();
        String method_name = proceedingJoinPoint.getSignature().getName();
        Span span = tracer.buildSpan(class_name + "." + method_name).withTag("class", class_name)
                .withTag("method", method_name).start();
        System.out.println("Start span");
        Object result = proceedingJoinPoint.proceed();
        span.finish();
        System.out.println("End span");
        return result;
    }
}
