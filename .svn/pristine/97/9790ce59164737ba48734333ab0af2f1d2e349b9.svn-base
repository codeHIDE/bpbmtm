package com.bypay.service.impl.util;




import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


public class JsonDateValueProcessor implements JsonValueProcessor {

    private String datePattern = "yyyy-MM-dd";
    public String getDatePattern() {
        return datePattern;
    }
    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

    public JsonDateValueProcessor() {
        super();
    }
    public JsonDateValueProcessor(String format) {
        super();
        this.datePattern = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    /**
     * process
     * @param value(Timestamp)(Date)
     * @return Object
     */
    private Object process(Object value) {
               return "";
    }
}
