package response;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class TextResponse implements Response {
	private Map<String, String> map = new HashMap<String, String>();

	public TextResponse(String UUID,String sender, String receiver,
			String content) {
		map.put("type", "response");
		map.put("sequence", UUID);
		map.put("action", "text");
		map.put("sender", sender);
		map.put("receiver", receiver);
		map.put("content", content);
		map.put("time",System.currentTimeMillis()+"");
	}

	@Override
	public String getData() {
		return new Gson().toJson(map);
	}

}
