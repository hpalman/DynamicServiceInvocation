package com.example.dynamicserviceinvocation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service("exampleService2")
@Slf4j
public class ExampleService2 implements CustomService {

    @Override
    @Transactional // 트랜잭션 선언
    public void execute(String param) {
        System.out.println("ExampleService2 executed with param: " + param);
    }
}
