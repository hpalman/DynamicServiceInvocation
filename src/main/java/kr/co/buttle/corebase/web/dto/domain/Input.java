package kr.co.buttle.corebase.web.dto.domain;

public class Input {
    protected String mapper;
    public String getMapper() {
        return mapper;
    }
    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    protected String method;
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    protected String statement;
    public String getStatement() {
        return statement;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }

    protected String resultname;
    public String getResultname() {
        return resultname;
    }
    public void setResultname(String resultname) {
        this.resultname = resultname;
    }

    protected Object header;
    public Object getHeader() {
        return header;
    }
    public void setHeader(Object header) {
        this.header = header;
    }

    protected Object source;
    public Object getSource() {
        return source;
    }
    public void setSource(Object source) {
        this.source = source;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Mapper : ").append(getMapper()).append(", ");
        stringBuilder.append("Method : ").append(getMethod()).append(", ");
        stringBuilder.append("Statement : ").append(getStatement()).append(", ");
        stringBuilder.append("Header : ").append(getHeader().toString()).append(", ");
        stringBuilder.append("Source : ").append(getSource().toString()).append(", ");
        stringBuilder.append("ResultName : ").append(getResultname());

        return stringBuilder.toString();
    }
}
