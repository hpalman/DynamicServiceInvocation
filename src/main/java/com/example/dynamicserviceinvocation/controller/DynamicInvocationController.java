package com.example.dynamicserviceinvocation.controller;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dynamicserviceinvocation.service.CustomService;

import kr.co.buttle.corebase.web.dto.OutputDto;
import kr.co.buttle.corebase.web.dto.domain.Output;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DynamicInvocationController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/invokeService")
    public ResponseEntity<OutputDto> invokeService(@RequestParam("serviceName") String serviceName, @RequestParam("param") String param) {
        OutputDto outputDto = new OutputDto();
        try {
            // ApplicationContext에서 서비스 빈을 찾아옴
            Object serviceBean = applicationContext.getBean(serviceName);

            // 해당 빈이 CustomService 인터페이스를 구현하는지 확인
            if (serviceBean instanceof CustomService) {
                // Reflection을 사용하여 메서드를 동적으로 호출
                Method method = serviceBean.getClass().getMethod("execute", String.class);
                Object object = method.invoke(serviceBean, param);

                Output output2 = new Output();
                output2.setResult(object);

                outputDto.addOutput("returnObject", output2);
                log.info("returnObject return...");
                return ResponseEntity.ok(outputDto);
            } else {
                log.info("Service not found or does not implement CustomService.");
                //return "Service not found or does not implement CustomService";
                return ResponseEntity.ok(outputDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return "Error invoking service: " + e.getMessage();
            log.error("Service not found or does not implement CustomService.");
            return ResponseEntity.ok(outputDto);
        }


/// Output output1 = new Output();
/// outputDto.addOutput("fireResourceKeys_0", output1);
///
/// Output output2 = new Output();
/// List<Map> listMap = new ArrayList(); //<HashMap<>>();
/// for (int i=0;i<3;i++) {
///     Map<String, Object> result = new HashMap<String,Object>();
///     result.put("A1", (Integer)3);
///     result.put("B1", "B1 value");
///     listMap.add(result);
/// }
/// output2.setResult(listMap);
/// outputDto.addOutput("fireResourceKeys_1", output2);
///
///         // return "Service invoked successfully";
///         return ResponseEntity.ok(outputDto);
    }
}
