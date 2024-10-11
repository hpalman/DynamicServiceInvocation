package com.example.dynamicserviceinvocation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.buttle.corebase.web.dto.InputDto;
import kr.co.buttle.corebase.web.dto.OutputDto;
import kr.co.buttle.corebase.web.dto.domain.Head;
import kr.co.buttle.corebase.web.dto.domain.Metadata;
import kr.co.buttle.corebase.web.dto.domain.Output;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @PostMapping("/noTransaction.do")
    public ResponseEntity<OutputDto> invoke(@RequestBody InputDto inputDto) {
        OutputDto outputDto = new OutputDto();
        Head head = outputDto.getHead();
        head.put("PGM_ID", "SYS1000");
        head.setUserId("0003402344");
        head.setResponseTime(null);
        head.setResponseType(null);

        Output output = new Output();
        Metadata metadata = output.getMetadata();
        metadata.setMethod("comcode");
        metadata.setResultname("a");
        metadata.setStatement("SEIZE.selectSmlcode");


        /// Output output2 = new Output();
        List<Map> listMap = new ArrayList(); //<HashMap<>>();
        for (int i=0;i<2;i++) {
            Map<String, Object> result = new HashMap<String,Object>();
            result.put("A1", (Integer)3);
            result.put("B1", "B1 value");
            listMap.add(result);
        }
        /// output2.setResult(listMap);
        /// outputDto.addOutput("fireResourceKeys_1", output2);
        output.setResult(listMap);


        outputDto.addOutput("a",  output);//.setOutput(null).getOutput();

      /// Output resultoutput1 = new Output();
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


        return ResponseEntity.ok(outputDto);
    }
}
