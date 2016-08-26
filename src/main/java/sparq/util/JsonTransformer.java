package sparq.util;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
	
//	private Gson gson = new Gson();
	
//	@Override
//    public String render(Object model) {
//        return gson.toJson(model);
//    }

    @Override
    public String render(final Object model) throws Exception {
        return JacksonUtil.newObjectMapper().writeValueAsString(model);
    }
}
