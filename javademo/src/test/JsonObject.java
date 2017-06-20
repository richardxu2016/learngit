package test;

import java.util.Map;
import java.util.WeakHashMap;

public class JsonObject {

	public static void main(String[] args) {
		JsonObject j = new JsonObject() ;
		Map<String,String> map = j.getJsonMap("{\"error\":0,\"x\":\"MTE2LjQ3NDIzMjQ2OTM4\",\"y\":\"MzkuOTYxMDY0NDMwNzE4\"}");
		System.out.println(map);
	}
	
	public Map<String,String> getJsonMap(String jsonStr) {
		String [] jsonArr = jsonStr.replace("{", "").replace("}", "")
				.replace("\"", "").replace("\"", "").split("\\,");
		Map<String,String> map = new WeakHashMap<String, String>();
		for (String key : jsonArr) {
			String [] s = key.split("\\:");
 			map.put(s[0], s[1]);
		}
		return map;
	}
}

class BaiduMap {
	private String error;
	private String x;
	private String y;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
}