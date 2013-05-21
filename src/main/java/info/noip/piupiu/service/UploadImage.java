package info.noip.piupiu.service;


import static org.apache.commons.io.IOUtils.toByteArray;
import info.noip.piupiu.dto.UploadImageResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

public class UploadImage {

	private DefaultHttpClient httpClient;
	private String key = "3ZFYDRMOa18b0d4c520a173c7443e4e8ec29095c";
	private HttpPost post;
	MultipartEntity entity;
	
	public UploadImage() {
		if(this.httpClient==null){
			this.httpClient = new DefaultHttpClient(); 
			this.post = new HttpPost("http://www.imageshack.us/upload_api.php");
		}
	} 
	
	public UploadImage attach(InputStream is) throws IOException{
		this.entity = new MultipartEntity();
		this.entity.addPart("key", new StringBody(this.key, Charset.forName("UTF-8")));
		this.entity.addPart("format", new StringBody("json", Charset.forName("UTF-8")));
		this.entity.addPart("fileupload", new ByteArrayBody(toByteArray(is), "fileupload"));
		return this;
	}

	public String send() throws ClientProtocolException, IOException {
		post.setEntity(entity);
		HttpResponse response = httpClient.execute(post);
		Reader in = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent(), "ISO-8859-1"));
		StringBuilder builder = new StringBuilder();
		char[] buf = new char[1000];
		int l = 0;
		while (l >= 0) {
			builder.append(buf, 0, l);
			l = in.read(buf);
		}
		
		UploadImageResponse uiResponse = new Gson().fromJson(builder.toString(), UploadImageResponse.class);
		return uiResponse.getLinks().getImageLink();
	}
}
