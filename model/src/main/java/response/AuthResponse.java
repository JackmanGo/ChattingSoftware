package response;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class AuthResponse implements Response {
	private Map<String, String> map = new HashMap<String, String>();

	public AuthResponse(){

	}
	public AuthResponse(String UUID) {
		map.put("type", "response");
		map.put("sequence", UUID);
		map.put("action", "auth");
		map.put("content","欢迎使用，如果你在使用过程中遇到问题请记得及时向我反馈哦!");
		map.put("sender","系统消息");
		map.put("time",System.currentTimeMillis()+"");
	}

	@Override
	public String getData() {
		return new Gson().toJson(map);
	}

}
