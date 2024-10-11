package kr.co.buttle.corebase.web.dto;

import kr.co.buttle.corebase.web.dto.domain.Head;

public class Dto {
    protected Head head;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Dto() {
        this.head = new Head();
    }
}
