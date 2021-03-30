package com.ohrlings.arrowheadadapter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/adapter")
public class AdapterController implements Controller {

	Logger logger = LoggerFactory.getLogger(AdapterController.class);

	// RequestBody exist in both springframework and okhttp. That's why I use classpath
	@Override
	@PostMapping
	public ResponseEntity<String> pushData(@org.springframework.web.bind.annotation.RequestBody String inputJSON) {
		logger.info(inputJSON);

		// These values are set up to be used with mock-server - https://www.mock-server.com/
		// java -jar <path to jar>mockserver-netty-5.11.2-jar-with-dependencies.jar -serverPort 8585 // To start
		// http://127.0.0.1:8585/mockserver/dashboard // To monitor calls.
		// Real values are to be fetched from service registry
		String protocol = "http";
		String host = "127.0.0.1";
		String port = "8585";
		String path = "/test";

		if(!path.startsWith("/")) {
			path = "/" + path;
		}
		
		String url = protocol + "://" + host + ":" + port + path;

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("text/plain");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, inputJSON);
		Request request = new Request.Builder().url(url).post(body)
//		  .addHeader("cache-control", "no-cache")
//		  .addHeader("postman-token", "e11ce033-931a-0419-4903-ab860261a91a")
				.build();

		Response response = null;

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(inputJSON, HttpStatus.valueOf(response.code()));
		}

		return new ResponseEntity<>(inputJSON, HttpStatus.valueOf(response.code()));
	}
}
