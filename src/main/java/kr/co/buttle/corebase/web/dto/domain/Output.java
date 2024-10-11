package kr.co.buttle.corebase.web.dto.domain;

import java.util.List;

public class Output {
    protected Object result;
    protected Metadata metadata;

    public Output() {
        this.metadata = new Metadata();
        metadata.put(Metadata.RESULT_COUNT, 0);
    }

    public Output(String resultname, String method, String statement) {
        this();

        this.metadata.setResultname(resultname);
        this.metadata.setMethod(method);
        this.metadata.setStatement(statement);
    }

    public Output(Input input) {
        this(input.getResultname(), input.getMethod(), input.getStatement());
    }

    public Object getResult() {
        return result;
    }

    @SuppressWarnings("rawtypes")
    public void setResult(Object result) {
        this.result = result;

        if (result == null) {
            metadata.setResultCount(0);
        } else if (result instanceof List) {
            List resultList = (List) result;
            metadata.setResultCount(resultList.size());
        } else {
            metadata.setResultCount(1);
        }
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error : ").append(metadata.isError()).append(", ");
        stringBuilder.append("Message : ").append(metadata.getMessage()).append(", ");
        stringBuilder.append("ResultCount : ").append(metadata.getResultCount()).append(", ");
        stringBuilder.append("Result ").append(getResult().toString()).append(", ");

        return stringBuilder.toString();
    }
}
