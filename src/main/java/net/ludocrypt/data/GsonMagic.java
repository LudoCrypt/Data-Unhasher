package main.java.net.ludocrypt.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class GsonMagic {
	private static final Gson GSON = (new GsonBuilder()).create();

	public static boolean isStringValue(JsonObject json, String string) {
		return !isValidPrimitive(json, string) ? false : json.getAsJsonPrimitive(string).isString();
	}

	public static boolean isStringValue(JsonElement element) {
		return !element.isJsonPrimitive() ? false : element.getAsJsonPrimitive().isString();
	}

	public static boolean isNumberValue(JsonElement element) {
		return !element.isJsonPrimitive() ? false : element.getAsJsonPrimitive().isNumber();
	}

	public static boolean isBooleanValue(JsonObject json, String string) {
		return !isValidPrimitive(json, string) ? false : json.getAsJsonPrimitive(string).isBoolean();
	}

	public static boolean isArrayNode(JsonObject json, String string) {
		return !isValidNode(json, string) ? false : json.get(string).isJsonArray();
	}

	public static boolean isValidPrimitive(JsonObject json, String string) {
		return !isValidNode(json, string) ? false : json.get(string).isJsonPrimitive();
	}

	public static boolean isValidNode(JsonObject json, String string) {
		if (json == null) {
			return false;
		} else {
			return json.get(string) != null;
		}
	}

	public static String toString(JsonElement element, String string) {
		if (element.isJsonPrimitive()) {
			return element.getAsString();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a string, was " + getType(element));
		}
	}

	public static String getAsString(JsonObject json, String string) {
		if (json.has(string)) {
			return toString(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a string");
		}
	}

	public static String getAsString(JsonObject json, String string, String string2) {
		return json.has(string) ? toString(json.get(string), string) : string2;
	}

	public static boolean toBoolean(JsonElement element, String string) {
		if (element.isJsonPrimitive()) {
			return element.getAsBoolean();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a Boolean, was " + getType(element));
		}
	}

	public static boolean getAsBoolean(JsonObject json, String string) {
		if (json.has(string)) {
			return toBoolean(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a Boolean");
		}
	}

	public static boolean getAsBoolean(JsonObject json, String string, boolean bool) {
		return json.has(string) ? toBoolean(json.get(string), string) : bool;
	}

	public static float toFloat(JsonElement element, String string) {
		if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			return element.getAsFloat();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a Float, was " + getType(element));
		}
	}

	public static float getAsFloat(JsonObject json, String string) {
		if (json.has(string)) {
			return toFloat(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a Float");
		}
	}

	public static float getAsFloat(JsonObject json, String string, float f) {
		return json.has(string) ? toFloat(json.get(string), string) : f;
	}

	public static long toLong(JsonElement element, String string) {
		if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			return element.getAsLong();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a Long, was " + getType(element));
		}
	}

	public static long getAsLong(JsonObject json, String string) {
		if (json.has(string)) {
			return toLong(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a Long");
		}
	}

	public static long getAsLong(JsonObject json, String string, long l) {
		return json.has(string) ? toLong(json.get(string), string) : l;
	}

	public static int toInt(JsonElement element, String string) {
		if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			return element.getAsInt();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a Int, was " + getType(element));
		}
	}

	public static int getAsInt(JsonObject json, String string) {
		if (json.has(string)) {
			return toInt(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a Int");
		}
	}

	public static int getAsInt(JsonObject json, String string, int i) {
		return json.has(string) ? toInt(json.get(string), string) : i;
	}

	public static byte toByte(JsonElement element, String string) {
		if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
			return element.getAsByte();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a Byte, was " + getType(element));
		}
	}

	public static byte getAsByte(JsonObject json, String string, byte b) {
		return json.has(string) ? toByte(json.get(string), string) : b;
	}

	public static JsonObject toJsonObject(JsonElement element, String string) {
		if (element.isJsonObject()) {
			return element.getAsJsonObject();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a JsonObject, was " + getType(element));
		}
	}

	public static JsonObject getAsJsonObject(JsonObject json, String string) {
		if (json.has(string)) {
			return toJsonObject(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a JsonObject");
		}
	}

	public static JsonObject getAsJsonObject(JsonObject json, String string, JsonObject json2) {
		return json.has(string) ? toJsonObject(json.get(string), string) : json2;
	}

	public static JsonArray toJsonArray(JsonElement element, String string) {
		if (element.isJsonArray()) {
			return element.getAsJsonArray();
		} else {
			throw new JsonSyntaxException("Expected " + string + " to be a JsonArray, was " + getType(element));
		}
	}

	public static JsonArray getAsJsonArray(JsonObject json, String string) {
		if (json.has(string)) {
			return toJsonArray(json.get(string), string);
		} else {
			throw new JsonSyntaxException("Missing " + string + ", expected to find a JsonArray");
		}
	}

	public static JsonArray getAsJsonArray(JsonObject json, String string, JsonArray jsonArray) {
		return json.has(string) ? toJsonArray(json.get(string), string) : jsonArray;
	}

	public static <T> T toObject(JsonElement element, String string, JsonDeserializationContext jsonDeserializationContext, Class<? extends T> gsonClass) {
		if (element != null) {
			return jsonDeserializationContext.deserialize(element, gsonClass);
		} else {
			throw new JsonSyntaxException("Missing " + string);
		}
	}

	public static <T> T getAsObject(JsonObject json, String string, JsonDeserializationContext jsonDeserializationContext, Class<? extends T> gsonClass) {
		if (json.has(string)) {
			return toObject(json.get(string), string, jsonDeserializationContext, gsonClass);
		} else {
			throw new JsonSyntaxException("Missing " + string);
		}
	}

	public static <T> T getAsObject(JsonObject json, String string, T object, JsonDeserializationContext jsonDeserializationContext, Class<? extends T> gsonClass) {
		return json.has(string) ? toObject(json.get(string), string, jsonDeserializationContext, gsonClass) : object;
	}

	public static String getType(JsonElement element) {
		String string = StringUtils.abbreviateMiddle(String.valueOf(element), "...", 10);
		if (element == null) {
			return "null (missing)";
		} else if (element.isJsonNull()) {
			return "null (json)";
		} else if (element.isJsonArray()) {
			return "an array (" + string + ")";
		} else if (element.isJsonObject()) {
			return "an object (" + string + ")";
		} else {
			if (element.isJsonPrimitive()) {
				JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();
				if (jsonPrimitive.isNumber()) {
					return "a number (" + string + ")";
				}

				if (jsonPrimitive.isBoolean()) {
					return "a boolean (" + string + ")";
				}
			}

			return string;
		}
	}

	public static <T> T fromJson(Gson gson, Reader reader, Class<T> gsonClass, boolean bool) {
		try {
			JsonReader jsonReader = new JsonReader(reader);
			jsonReader.setLenient(bool);
			return gson.getAdapter(gsonClass).read(jsonReader);
		} catch (IOException var5) {
			throw new JsonParseException(var5);
		}
	}

	public static <T> T fromJson(Gson gson, Reader reader, TypeToken<T> typeToken, boolean bool) {
		try {
			JsonReader jsonReader = new JsonReader(reader);
			jsonReader.setLenient(bool);
			return gson.getAdapter(typeToken).read(jsonReader);
		} catch (IOException var5) {
			throw new JsonParseException(var5);
		}
	}

	public static <T> T fromJson(Gson gson, String string, TypeToken<T> typeToken, boolean bool) {
		return (T) fromJson(gson, (Reader) (new StringReader(string)), (TypeToken) typeToken, bool);
	}

	public static <T> T fromJson(Gson gson, String string, Class<T> gsonClass, boolean bool) {
		return (T) fromJson(gson, (Reader) (new StringReader(string)), (Class) gsonClass, bool);
	}

	public static <T> T fromJson(Gson gson, Reader reader, TypeToken<T> typeToken) {
		return fromJson(gson, reader, typeToken, false);
	}

	public static <T> T fromJson(Gson gson, String string, TypeToken<T> typeToken) {
		return fromJson(gson, string, typeToken, false);
	}

	public static <T> T fromJson(Gson gson, Reader reader, Class<T> gsonClass) {
		return fromJson(gson, reader, gsonClass, false);
	}

	public static <T> T fromJson(Gson gson, String string, Class<T> gsonClass) {
		return fromJson(gson, string, gsonClass, false);
	}

	public static JsonObject parse(String string, boolean bool) {
		return parse((Reader) (new StringReader(string)), bool);
	}

	public static JsonObject parse(Reader reader, boolean bool) {
		return (JsonObject) fromJson(GSON, reader, JsonObject.class, bool);
	}

	public static JsonObject parse(String string) {
		return parse(string, false);
	}

	public static JsonObject parse(Reader reader) {
		return parse(reader, false);
	}
}
