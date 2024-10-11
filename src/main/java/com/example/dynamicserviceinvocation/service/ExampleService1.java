package com.example.dynamicserviceinvocation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service("exampleService1")
@Slf4j
public class ExampleService1 implements CustomService {

    @Override
    @Transactional // 트랜잭션 선언
    public void execute(String param) {
        log.info("ExampleService1 executed with param: " + param);

        // 예외 발생 시 트랜잭션 롤백
        if ("error".equalsIgnoreCase(param)) {
            throw new RuntimeException("Forced exception to trigger rollback");
        }
    }
}
