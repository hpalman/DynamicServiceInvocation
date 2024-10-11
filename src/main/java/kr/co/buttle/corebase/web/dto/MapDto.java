package kr.co.buttle.corebase.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/// import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

//import atg.taglib.json.util.JSONArray;
//import atg.taglib.json.util.JSONException;
//import atg.taglib.json.util.JSONObject;
//import kr.co.buttle.corebase.web.exception.CBException;

@SuppressWarnings({ "rawtypes" })

public class MapDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3002444039912813497L;
    protected static Logger ioLogger = LoggerFactory.getLogger("ioLogger");

    Map source = null;

    public MapDto() {
        this.source = new HashMap();
    }

    public void setSource(Map source) {
        this.source = source;
    }

    @SuppressWarnings("unchecked")
    public void setSource(String jsonString) /* throws JSONException */{
        Gson gson = new Gson();
        HashMap map = gson.fromJson(jsonString, HashMap.class);
    	this.source.putAll(map); // new JSONObject(jsonString).toHashMap());
    }

    @SuppressWarnings("unchecked")
    public MapDto(MapDto source) {
        this.source = new HashMap();
        this.source.putAll(source.getSource());
    }

    @SuppressWarnings("unchecked")
    public MapDto(String jsonString) /* throws JSONException */{
        this();
        setSource(jsonString);
    	//this.source = new HashMap();
    	//this.source.putAll(new JSONObject(jsonString).toHashMap());
    }

    public MapDto(Throwable e) {
        this();
        this.isError = true;
        this.errorMessage = e.getMessage();
    }

    public MapDto(Exception e) {
        this();
        this.isError = true;
        this.errorMessage = e.getMessage();
    }

    @SuppressWarnings("unchecked")
    public MapDto(Map source) {
        this.source = new HashMap();
        this.source.putAll(source);
    }

    @SuppressWarnings("unchecked")
    public MapDto(Object source) /* throws CBException */ {
        if (source == null) {
            this.source = new HashMap();
            return;
        }

        int type = getType(source);
        ///if (type == JSONOBJECT) {
        ///    this.source = new HashMap();
        ///    this.source.putAll(((JSONObject) source).toHashMap());
        ///}
        ///else
        if (type == MAPDTO) {
            this.source = new HashMap();
            this.source.putAll(((MapDto) source).getSource());
        } else if (type == MAP) {
            this.source = new HashMap();
            this.source.putAll((Map) source);
        }
        ///else {
        ///    throw new CBException(String.format("MapDto소스 (%s)에 정상적인 값이 전달되지 않았습니다.", source));
        ///}
    }

    public Map getSource() {
        return source;
    }

    /// public JSONObject getJsonSource() {
    ///     return new JSONObject(this.getSource());
    /// }

    public static MapDto init(Object source)/* throws CBException */{
        if (source == null) {
            return new MapDto();
        }

        int type = getType(source);
        ///if (type == JSONOBJECT) {
        ///    return init(((JSONObject) source).toHashMap());
        ///} else
        if (type == MAPDTO) {
            return (MapDto) source;// ).getSource());
        } else if (type == MAP) {
            return init((Map) source);
        }
        ///else {
        ///    throw new CBException(String.format("MapDto소스 (%s)에 정상적인 값이 전달되지 않았습니다.", source));
        ///}
return init((Map) source);
    }

    public static MapDto init(Map source) {
        if (source == null) {
            return new MapDto();
        }

        MapDto mapDto = new MapDto();
        mapDto.setSource(source);
        return mapDto;
    }

    public final static int INTEGER = 0;
    public final static int STRING = 1;
    public final static int FLOAT = 2;
    public final static int LIST = 3;
    public final static int BIGDECIMAL = 5;
    public final static int BOOLEAN = 6;
    public final static int MAP = 10;
    ///public final static int JSONOBJECT = 20;
    ///public final static int JSONARRAY = 30;
    public final static int MAPDTO = 40;

    public static int getType(Object data) {
        if (data == null) {
            return -1;
        }

        if (data instanceof MapDto) {
            return MAPDTO;
        }

        if (data instanceof Integer) {
            return INTEGER;
        }

        if (data instanceof BigDecimal) {
            return BIGDECIMAL;
        }

        if (data instanceof Boolean) {
            return BOOLEAN;
        }

        if (data instanceof String) {
            return STRING;
        }

        if (data instanceof Float) {
            return FLOAT;
        }

        if (data instanceof List) {
            return LIST;
        }

        if (data instanceof Map) {
            return MAP;
        }

        /// if (data instanceof JSONObject) {
        ///     return JSONOBJECT;
        /// }
        ///
        /// if (data instanceof JSONArray) {
        ///     return JSONARRAY;
        /// }

        // if (ioLogger.isDebugEnabled()) {
        // ioLogger.debug(data + " : " + data.getClass());
        // }

        return -1;
    }

    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        this.source.put(key, value);
    }

    /**
     * source에 값을 설정한다.
     *
     * @param source
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void setValue(Map source, String key, Object value) {
        source.put(key, value);
    }

    /**
     * source에 값을 설정한다.
     *
     * @param source
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void setValue(Object source, String key, Object value) {
        if (getType(source) == MAP) {
            ((Map) source).put(key, value);
        }
    }

    /**
     * source의 map 형식 데이터 필드에 값을 넣는다.
     *
     * @param source
     * @param target
     * @param key
     * @param value
     * @throws CBException
     */
    @SuppressWarnings("unchecked")
    public static void setValue(Map source, String target, String key, Object value)/* throws CBException */{
        getMap(source, target).put(key, value);
    }

    /**
     * sourc에 값을 넣는다.
     *
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public void setValue(String key, Object value) {
        source.put(key, value);
    }

    /**
     * source의 map 형식 데이터 필드에 값을 넣는다.
     *
     * @param target
     * @param key
     * @param value
     * @throws CBException
     */
    @SuppressWarnings("unchecked")
    public void setValue(String target, String key, Object value)/* throws CBException */{
        getMap(source, target).put(key, value);
    }

    public String toString() {
        return source.toString();
    }

    public String toJsonString() {
    	return new Gson().toJson(source);
    }

    /**
     * 내장 변수 source에서 boolean형 데이터를 반환한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public boolean is(String key) /* throws CBException */ {
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}
        Object data = source.get(key);
        int type = getType(data);
        if (type == BOOLEAN) {
            return (Boolean) source.get(key);
        }
        ///throw new CBException(String.format("형식이 일치하지 않습니다. (class : %s, type : %s)", data.getClass(), type));
return false;
    }

    /**
     * 내장 변수 source에서 boolean형 데이터를 반환한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public boolean is(String key, boolean defaultValue) {
        //try {
            return is(key);
        //} catch (CBException cbde) {
        //    return defaultValue;
        //}
    }

    /**
     * 내장 변수 source에서 boolean형 데이터를 반환한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public boolean hasKey(String key) {
        return this.source.containsKey(key);
    }

    /**
     * 데이터가 비었는가?
     *
     * @return
     */
    public boolean isEmpty() {
        return this.source.size() == 0;
    }

    public boolean isEmpty(String key) {

        boolean is = isNull(key);
        if (is) {
            return true;
        }
        return optString(key).equalsIgnoreCase("");
    }

    public boolean isNotEmpty(String key) {
        return !isNull(key);
    }

    public boolean isNull(String key) {
        if (source == null || !source.containsKey(key)) {
            return true;
        }

        if (source.get(key) == null) {
            return true;
        }
        return false;
    }

    /**
     * 내장 변수 source에서 숫자형 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public int getInt(String key) /* throws CBException */ {
        return getInt(source, key);
    }

    /**
     * 내장 변수 source에서 숫자형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본숫자를 반환한다.
     *
     * @param key
     * @param intDefault
     * @return
     */
    public int optInt(String key, int intDefault) {
        return optInt(source, key, intDefault);
    }

    /**
     * 내장 변수 source에서 숫자형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본숫자(0)를 반환한다.
     *
     * @param key
     * @return
     */
    public int optInt(String key) {
        return optInt(source, key, 0);
    }

    /**
     * source에서 숫자형 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */
    public static int getInt(Map source, String key)/* throws CBException */{
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}

        Object data = source.get(key);
        int type = getType(data);
        /// if (type == -1) {
        ///     throw new CBException("반환값이 null입니다.");
        /// }
        if (type == INTEGER) {
            return (int) data;
        }

        if (type == BIGDECIMAL) {
            return new BigDecimal(data.toString()).intValue();
        }

        ///if (type == STRING && StringUtils.isNumeric((String) data)) {
            return Integer.parseInt((String) data);
        ///}
        ///throw new CBException(String.format("형식이 일치하지 않습니다. (class : %s, type : %s)", data.getClass(), type));
    }

    /**
     * source에서 숫자형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본숫자를 반환한다.
     *
     * @param source
     * @param key
     * @param intDefault
     * @return
     */

    public static int optInt(Map source, String key, int intDefault) {
        ///try {
            return getInt(source, key);
        ///} catch (CBException cbde) {
        ///    if (ioLogger.isDebugEnabled()) {
        ///        ioLogger.debug(cbde.getMessage());
        ///    }
        ///    return intDefault;
        ///}

    }

    /**
     * 내장 변수 source에서 string 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public String getString(String key)/* throws CBException */{
        return getString(source, key);
    }

    /**
     * 내장 변수 source에서 string 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본값을 반환한다.
     *
     * @param key
     * @param strDefault
     * @return
     */
    public String optString(String key, String strDefault) {
        return optString(source, key, strDefault);
    }

    /**
     * 내장 변수 source에서 string 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본값("")을 반환한다.
     *
     * @param key
     * @return
     */
    public String optString(String key) {
        return optString(source, key, "");
    }

    /**
     * source에서 string 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */

    public static String getString(Map source, String key)/* throws CBException */{
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}

        Object data = source.get(key);
        if (data == null) {
            return null;
        }

        int type = getType(data);
        if (type == STRING) {
            return (String) data;
        }

        return data.toString();
    }

    /**
     * source에서 string 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */
    public static String optString(Map source, String key, String strDefault) {
        ///try {
            String output = getString(source, key);
            if (output == null) {
                return strDefault;
            }
            return output;
        ///} catch (CBException cbde) {
        ///    if (ioLogger.isDebugEnabled()) {
        ///        ioLogger.debug(cbde.getMessage());
        ///    }
        ///    return strDefault;
        ///}
    }

    public static String optString(Map source, String key) {
        return optString(source, key, "");
    }

    /**
     * 내장 변수 source에서 string 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public String getString(String target, String key)/* throws CBException */{
        return MapDto.optString(optMap(target), key);
    }

    /**
     * source에서 string 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */

    public static String getString(Map source, String target, String key)/* throws CBException */{
        return MapDto.optString(MapDto.optMap(source, target), key);
    }

    /**
     * 내장 변수 source에서 float형 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */
    public float getFloat(String key)/* throws CBException */{
        return getFloat(source, key);
    }

    /**
     * 내장 변수 source에서 float형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본float를 반환한다.
     *
     * @param key
     * @param intDefault
     * @return
     */
    public float optFloat(String key, float floatDefault) {
        return optFloat(source, key, floatDefault);
    }

    /**
     * 내장 변수 source에서 float형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본float를 반환한다.
     *
     * @param key
     * @return
     */
    public float optFloat(String key) {
        return optFloat(source, key, 0.0f);
    }

    /**
     * source에서 float형 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */

    public static float getFloat(Map source, String key)/* throws CBException */{
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}

        Object data = source.get(key);

        int type = getType(data);
        if (type == FLOAT) {
            return (float) source.get(key);
        }

        if (type == INTEGER) {
            return Float.parseFloat(data.toString());
        }

        if (type == BIGDECIMAL) {
            return Float.parseFloat(data.toString());
        }

        ///if (type == STRING && StringUtils.isNumeric((String) data)) {
            return Float.parseFloat((String) data);
        ///}
        ///throw new CBException(String.format("형식이 일치하지 않습니다. (class : %s, type : %s)", data.getClass(), type));
    }

    /**
     * source에서 float형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본float를 반환한다.
     *
     * @param source
     * @param key
     * @param intDefault
     * @return
     */

    public static float optFloat(Map source, String key, float floatDefault) {
        ///try {
            return getFloat(source, key);
        ///} catch (CBException cbde) {
        ///    if (ioLogger.isDebugEnabled()) {
        ///        ioLogger.debug(cbde.getMessage());
        ///    }
        ///    return floatDefault;
        ///}
    }

    /**
     * 내장 변수 source에서 List 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */

    public List getList(String key)/* throws CBException */{
        return getList(source, key);
    }

    /**
     * 내장 변수 source에서 List 데이터를 반환한다. 정보가 없거나 오류 시 새로운 List를 반환한다.
     *
     * @param key
     * @param intDefault
     * @return
     */

    public List optList(String key) {
        return optList(source, key);
    }

    /**
     * source에서 List 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */

    public static List getList(Map source, String key)/* throws CBException */{
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}

        Object data = source.get(key);
        int type = getType(data);
        ///if (type == LIST) {
            return (List) source.get(key);
        ///}
        ///throw new CBException(String.format("형식이 일치하지 않습니다. (class : %s, type : %s)", data.getClass(), type));
    }

    /**
     * source에서 float형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본float를 반환한다.
     *
     * @param source
     * @param key
     * @param intDefault
     * @return
     */
    public static List optList(Map source, String key) {
        ///try {
            return getList(source, key);
        ///} catch (CBException cbde) {
        ///    if (ioLogger.isDebugEnabled()) {
        ///        ioLogger.debug(cbde.getMessage());
        ///    }
        ///    return new ArrayList();
        ///}
    }

    public static List getList(Map source, String target, String key) {
        return optList(optMap(source, target), key);
    }

    public List getList(String target, String key) {
        return optList(optMap(target), key);
    }

    @SuppressWarnings("unchecked")
    public static void addList(Map source, String key, List data) {
        if (data.size() > 0) {
            optList(source, key).addAll(data);
        }
    }

    @SuppressWarnings("unchecked")
    public static void addList(Map source, String key, Map data) {
        if (data.size() > 0) {
            optList(source, key).add(data);
        }
    }

    public void addList(String key, List data) {
        addList(source, key, data);
    }

    public void addList(String key, Map data) {
        addList(source, key, data);
    }

    public void addList(String key, MapDto data) {
        addList(source, key, data.getSource());
    }

    public static void addList(Map source, String target, String key, List data) {
        addList(optMap(source, target), key, data);
    }

    public static void addList(Map source, String target, String key, Map data) {
        addList(optMap(source, target), key, data);
    }

    public void addList(String target, String key, List data) {
        addList(optMap(target), key, data);
    }

    public void addList(String target, String key, Map data) {
        addList(optMap(target), key, data);
    }

    public static int getListSize(Map source, String target, String key) {
        return optList(optMap(source, target), key).size();
    }

    public int getListSize(String target, String key) {
        return optList(optMap(source, target), key).size();
    }

    public static int getListSize(Map source, String key) {
        return optList(source, key).size();
    }

    public int getListSize(String key) {
        return optList(source, key).size();
    }

    /**
     * 내장 변수 source에서 Map 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을
     * 발생한다.
     *
     * @param key
     * @return
     * @throws CBException
     */

    public Map getMap(String key)/* throws CBException */{
        return getMap(source, key);
    }

    /**
     * 내장 변수 source에서 Map 데이터를 반환한다. 정보가 없거나 오류 시 새로운 Map를 반환한다.
     *
     * @param key
     * @param intDefault
     * @return
     */

    public Map optMap(String key) {
        return optMap(source, key);
    }

    /**
     * source에서 Map 데이터를 반환한다. 대상 필드가 없을 경우 또는 형식에 맞지않은 정보는 exception을 발생한다.
     *
     * @param source
     * @param key
     * @return
     * @throws CBException
     */
    public static Map getMap(Map source, String key)/* throws CBException */{
        ///if (source == null || !source.containsKey(key)) {
        ///    throw new CBException(String.format("전달된 값이 null이거나 지정된 키(%s)를 찾을 수 없습니다.", key));
        ///}

        Object data = source.get(key);
        int type = getType(data);
        if (type == MAP) {
            return (Map) source.get(key);
        }
return (Map) source.get(key);

        ///if (type == JSONOBJECT) {
        ///    return ((JSONObject) source.get(key)).toHashMap();
        ///}
        ///throw new CBException(String.format("형식이 일치하지 않습니다. (class : %s, type : %s)", data.getClass(), type));
    }

    /**
     * source에서 float형 데이터를 반환한다. 정보가 없거나 오류 시 지정된 기본float를 반환한다.
     *
     * @param source
     * @param key
     * @param intDefault
     * @return
     */

    public static Map optMap(Map source, String key) {
        ///try {
            return getMap(source, key);
        ///} catch (CBException cbde) {
        ///    if (ioLogger.isDebugEnabled()) {
        ///        ioLogger.debug(cbde.getMessage());
        ///    }
        ///    return new HashMap();
        ///}
    }

    @SuppressWarnings("unchecked")
    public void putAll(Map source) {
    	if(source == null) {
    		return;
    	}
        this.source.putAll(source);
    }

    public MapDto append(MapDto mapDto) {
        putAll(mapDto.getSource());
        return this;
    }

    public MapDto append(Map source) {
        putAll(source);
        return this;
    }

    public MapDto getMapDto(String key) {
        return MapDto.init(optMap(key));
    }

    public static MapDto getMapDto(Map source, String key) {
        return MapDto.init(optMap(source, key));
    }

    public boolean isError = false;

    public boolean isError() {
        return this.isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    String errorMessage = "";

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Iterator keiterator() {
        return this.source.keySet().iterator();
    }


    public boolean has(String key) {
    	return this.source.containsKey(key);
    }
}
