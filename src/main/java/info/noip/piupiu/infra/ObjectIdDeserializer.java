package info.noip.piupiu.infra;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ObjectIdDeserializer implements JsonDeserializer<ObjectId> {
	@Override
	public ObjectId deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			return new ObjectId(json.getAsString());
		} catch (Exception e) {
			return null;
		}
	}
}
