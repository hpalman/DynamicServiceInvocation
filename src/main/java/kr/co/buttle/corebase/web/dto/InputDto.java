package kr.co.buttle.corebase.web.dto;

import java.util.ArrayList;
import java.util.List;

import kr.co.buttle.corebase.web.dto.domain.Input;

public class InputDto extends Dto {
    protected List<Input> input;

    public List<Input> getInput() {
        return input;
    }

    public void setInput(List<Input> input) {
        this.input = input;
    }

    public InputDto() {
        this.input = new ArrayList<Input>();
    }
}
