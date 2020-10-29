package kr.lineus.aipalm.exceptionhandler;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.io.IOException;


public class WebappException extends Exception {
	
	
	HttpStatus status;
	
	int code; 
		
	String link;
	
	String developerMessage;	
	
	/**
	 * 
	 * @param status
	 * @param code
	 * @param message
	 * @param developerMessage
	 * @param link
	 */
	public WebappException(HttpStatus status, int code, String message,
			String developerMessage, String link) {
		super(message);
		this.status = status;
		this.code = code;
		this.developerMessage = developerMessage;
		this.link = link;
	}


	
	public WebappException() { }
	
	public static WebappException getAppException(String errorResponse) {
		//{"status":500,"code":9999,"message":"ResetPassword failed. Status code 406","link":"","developerMessage":"
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode a;
		try {
			a = mapper.readTree(errorResponse);
			HttpStatus status = HttpStatus.valueOf(a.get("status").asInt());
			int code = a.get("code").asInt();
			String message = a.get("message").asText();
			String developerMessage = a.get("developerMessage").asText();
			String link = a.get("link").asText();
			WebappException e = new WebappException(status, code, message, developerMessage, link);
			return e;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public HttpStatus getStatus() {
		return status;
	}

	public WebappException setStatus(HttpStatus status) {
		this.status = status;
		return this;
	}

	public int getCode() {
		return code;
	}

	public WebappException setCode(int code) {
		this.code = code;
		return this;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public WebappException setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
		return this;
	}

	public String getLink() {
		return link;
	}

	public WebappException setLink(String link) {
		this.link = link;
		return this;
	}
	
	public WebappException addMessageParams(Object... params) {
		return new WebappException(status, code, String.format(this.getMessage(), params), developerMessage, link);
	}
	
}