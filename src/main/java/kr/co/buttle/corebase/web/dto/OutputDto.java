package kr.co.buttle.corebase.web.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import kr.co.buttle.corebase.web.dto.domain.Input;
import kr.co.buttle.corebase.web.dto.domain.Output;

public class OutputDto extends Dto {
    protected Map<String, Output> output;

    public Map<String, Output> getOutput() {
        return output;
    }

    public void setOutput(Map<String, Output> output) {
        this.output = output;
        calculateResponseCount();
    }

    public void addOutput(String name, Output output) {
        this.output.put(name, output);
        calculateResponseCount();
    }

    public void addOutput(Input input, Output output) {
        addOutput(input.getResultname(), output);
    }

    protected void calculateResponseCount() {
        head.setResponseCount(output.size());
    }

    public OutputDto() {
        this.output = new LinkedHashMap<String, Output>();
    }

}
