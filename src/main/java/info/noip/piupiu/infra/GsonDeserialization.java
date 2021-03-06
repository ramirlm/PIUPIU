package info.noip.piupiu.infra;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.deserialization.Deserializer;
import br.com.caelum.vraptor.deserialization.Deserializes;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.ResultException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Deserializes({ "application/json", "json" })
public class GsonDeserialization implements Deserializer {

	private static final Logger logger = LoggerFactory.getLogger(GsonDeserialization.class);

	private final ParameterNameProvider paramNameProvider;

	public GsonDeserialization(ParameterNameProvider paramNameProvider) {
		this.paramNameProvider = paramNameProvider;
	}

	@Override
	public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
		Method jMethod = method.getMethod();
		Class<?>[] types = jMethod.getParameterTypes();
		if (types.length == 0) {
			throw new IllegalArgumentException(
					"Methods that consumes representations must receive just one argument");
		}

		Gson gson = getGson();
		Object[] params = new Object[types.length];
		String[] parameterNames = paramNameProvider.parameterNamesFor(jMethod);

		try {
			String content = getContentOfStream(inputStream);
			logger.debug("json retrieved: " + content);

			JsonParser parser = new JsonParser();
			JsonObject root = (JsonObject) parser.parse(content);

			for (int i = 0; i < types.length; i++) {
				String name = parameterNames[i];
				JsonElement node = root.get(name);
				if (node != null) {
					params[i] = gson.fromJson(node, types[i]);
				}
			}
		} catch (Exception e) {
			throw new ResultException("Unable to deserialize data", e);
		}

		return params;
	}

	protected Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		gsonBuilder.registerTypeAdapter(ObjectId.class, new ObjectIdDeserializer());
		return gsonBuilder.create();
	}

	private String getContentOfStream(InputStream input) throws IOException {
		StringBuilder content = new StringBuilder();

		byte[] buffer = new byte[1024];
		int readed = 0;
		while ((readed = input.read(buffer)) != -1) {
			content.append(new String(buffer, 0, readed));
		}

		return content.toString();
	}

}
