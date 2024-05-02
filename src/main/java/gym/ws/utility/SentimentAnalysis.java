package gym.ws.utility;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SentimentAnalysis {
	static String subscription_key_var;
	static String subscription_key;
	static String endpoint_var;
	static String endpoint;

    public static void Initialize() throws Exception {
        subscription_key = "59360e5522594c13b18e7b8c5c82eaab";
        endpoint = "https://gympeople.cognitiveservices.azure.com/";
    }

	static String path = "/text/analytics/v3.0/sentiment";

	public static String getTheSentiment(Documents documents) throws Exception {
		String text = new Gson().toJson(documents);
		byte[] encoded_text = text.getBytes("UTF-8");

		URL url = new URL(endpoint + path+"?language=es");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscription_key);
		connection.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(encoded_text, 0, encoded_text.length);
		wr.flush();
		wr.close();

		StringBuilder response = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();

		return response.toString();
	}

	public static String prettify(String json_text) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(json_text).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(json);
	}
	
	
	 public static String extractPolarityFromResponse(String jsonResponse) {
	        JsonParser parser = new JsonParser();
	        JsonObject json = parser.parse(jsonResponse).getAsJsonObject();

	        JsonArray documents = json.getAsJsonArray("documents");
	        JsonElement firstDocument = documents.get(0);
	        JsonObject documentObject = firstDocument.getAsJsonObject();

	        JsonElement sentiment = documentObject.get("sentiment");

	        return sentiment.getAsString();
	    }
	 
	 public static String translatePolarityToSpanish(String englishPolarity) {
		    switch (englishPolarity.toLowerCase()) {
		        case "positive":
		            return "positivo";
		        case "negative":
		            return "negativo";
		        case "neutral":
		            return "neutral";
		        default:
		            return "desconocido"; 
		    }
		}
}


