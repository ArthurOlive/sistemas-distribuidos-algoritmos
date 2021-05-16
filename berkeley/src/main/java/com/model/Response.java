  
package java.com.model;

import org.json.simple.JSONObject;

public class Response {
    private int code;
    private JSONObject data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public Response(int code, JSONObject data) {
        this.code = code;
        this.data = data;
    }
}