package response;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 16-3-29.
 */
public class AskForResponse implements Response{
    private  Map<String,String> map = new HashMap<String, String>();

    public AskForResponse(String sender,String receiver,String content){

        map.put("action","ask_for");
        map.put("sender",sender);
        map.put("receiver",receiver);
        map.put("time",System.currentTimeMillis()+"");
        map.put("content",content);
        map.put("type", "response");
    }

    @Override
    public String getData() {
        return new Gson().toJson(map);
    }
}
