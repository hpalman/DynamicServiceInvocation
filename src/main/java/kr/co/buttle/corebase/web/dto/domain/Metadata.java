package kr.co.buttle.corebase.web.dto.domain;

import java.util.HashMap;

public class Metadata extends HashMap<String, Object> {
    private static final long serialVersionUID = -8336284171855163673L;

    public static final String RESULTNAME   = "resultname";
    public static final String METHOD       = "method";
    public static final String STATEMENT    = "statement";
    public static final String RESULT_COUNT = "resultCount";
    public static final String MESSAGE      = "message";
    public static final String IS_ERROR     = "isError";

    public String getResultname() {
        return (String) get(RESULTNAME);
    }

    public void setResultname(String resultname) {
        put(RESULTNAME, resultname);
    }

    public String getMethod() {
        return (String) get(METHOD);
    }

    public void setMethod(String method) {
        put(METHOD, method);
    }

    public String getStatement() {
        return (String) get(STATEMENT);
    }

    public void setStatement(String statement) {
        put(STATEMENT, statement);
    }

    public int getResultCount() {
        Integer resultCount = (Integer) get(RESULT_COUNT);
        return resultCount != null ? resultCount : 0;
    }

    public void setResultCount(int resultCount) {
        put(RESULT_COUNT, resultCount);
    }

    public String getMessage() {
        return (String) get(MESSAGE);
    }

    public void setMessage(String message) {
        put(MESSAGE, message);
    }

    public boolean isError() {
        Boolean error = (Boolean) get(IS_ERROR);
        return error != null ? error : false;
    }

    public void setError(boolean error) {
        put(IS_ERROR, error);
    }
}
