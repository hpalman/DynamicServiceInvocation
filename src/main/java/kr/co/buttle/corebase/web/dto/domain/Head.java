package kr.co.buttle.corebase.web.dto.domain;

import java.util.HashMap;
import java.util.Map;

// import kr.co.buttle.corebase.core.util.StringUtil;

public class Head extends HashMap<String, Object> {
    private static final long serialVersionUID = 5506763069627612077L;

    public static final String APP_NAME         = "appName";
    public static final String ACTION           = "action";
    public static final String CLIENT_IP        = "clientIp";
    public static final String RESPONSE_TYPE    = "responseType";
    public static final String METHOD           = "method";
    public static final String RESPONSE_LEVEL   = "responseLevel";
    public static final String RESPONSE_TITLE   = "responseTitle";
    public static final String RESPONSE_DETAIL  = "responseDetail";
    public static final String RESPONSE_TIME    = "responseTime";
    public static final String RESPONSE_SESSION = "responseSession";
    public static final String USER_ID          = "userId";
    public static final String USER_NAME        = "userName";
    public static final String USER_GRADE       = "userGrade";
    public static final String CONTENT_TYPE     = "contentType";
    public static final String RESPONSE_COUNT   = "responseCount";

    public String getAppName() {
        return (String) get(APP_NAME);
    }

    public void setAppName(String appName) {
        put(APP_NAME, appName);
    }

    public String getAction() {
        return (String) get(ACTION);
    }

    public void setAction(String action) {
        put(ACTION, action);
    }

    public String getClientIp() {
        return (String) get(CLIENT_IP);
    }

    public void setClientIp(String clientIp) {
        put(CLIENT_IP, clientIp);
    }

    public String getResponseType() {
        return (String) get(RESPONSE_TYPE);
    }

    public void setResponseType(String responseType) {
        put(RESPONSE_TYPE, responseType);
    }

    public String getMethod() {
        return (String) get(METHOD);
    }

    public void setMethod(String method) {
        put(METHOD, method);
    }

    public String getResponseLevel() {
        return (String) get(RESPONSE_LEVEL);
    }

    public void setResponseLevel(String responseLevel) {
        put(RESPONSE_LEVEL, responseLevel);
    }

    public String getResponseTitle() {
        return (String) get(RESPONSE_TITLE);
    }

    public void setResponseTitle(String responseTitle) {
        put(RESPONSE_TITLE, responseTitle);
    }

    public String getResponseDetail() {
        return (String) get(RESPONSE_DETAIL);
    }

    public void setResponseDetail(String responseDetail) {
        put(RESPONSE_DETAIL, responseDetail);
    }

    public String getResponseTime() {
        return (String) get(RESPONSE_TIME);
    }

    public void setResponseTime(String responseTime) {
        put(RESPONSE_TIME, responseTime);
    }

    public String getResponseSession() {
        return (String) get(RESPONSE_SESSION);
    }

    public void setResponseSession(String responseSession) {
        put(RESPONSE_SESSION, responseSession);
    }

    public String getUserId() {
        return (String) get(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getUserName() {
        return (String) get(USER_NAME);
    }

    public void setUserName(String userName) {
        put(USER_NAME, userName);
    }

    public String getUserGrade() {
        return (String) get(USER_GRADE);
    }

    public void setUserGrade(String userGrade) {
        put(USER_GRADE, userGrade);
    }

    public String getContentType() {
        return (String) get(CONTENT_TYPE);
    }

    public void setContentType(String contentType) {
        put(CONTENT_TYPE, contentType);
    }

    public int getResponseCount() {
        Integer responseCount = (Integer) get(RESPONSE_COUNT);
        return responseCount != null ? responseCount : 0;
    }

    public void setResponseCount(int responseCount) {
        put(RESPONSE_COUNT, responseCount);
    }

    public static interface ResponseLevel {
        public static final String ERROR = "ERROR";
    }
public String nvl(Object object) {
    return object == null ? "" : (String) object; //entry.getValue()
}
    public Map<String, String> toStringMap() {
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<String, Object> entry : entrySet()) {
            map.put(entry.getKey(), /* StringUtil.*/ nvl(entry.getValue()).toString());
        }

        return map;
    }
}
