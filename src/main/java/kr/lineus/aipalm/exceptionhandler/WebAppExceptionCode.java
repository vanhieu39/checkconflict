package kr.lineus.aipalm.exceptionhandler;

import org.springframework.http.HttpStatus;


public class WebAppExceptionCode {

	//USER EXCEPTIONS
	public static WebappException USER_ALREADY_REGISTERED_400_4000 = new WebappException(HttpStatus.BAD_REQUEST, 4000, "User has already registered.", "", "");
	public static WebappException USER_LOGIN_FAILED_400_4003 = new WebappException(HttpStatus.BAD_REQUEST, 4003, "Login failed.", "", "");
	public static WebappException USER_NOTFOUND_400_4005 = new WebappException(HttpStatus.BAD_REQUEST, 4005, "User not found.", "", "");

	
	//COMMON
	public static WebappException COMMON_FILE_INVALIDPATH_400_9000 = new WebappException(HttpStatus.BAD_REQUEST, 9000, "Filename contains invalid path sequence %s", "","");
	public static WebappException COMMON_FILE_IOEXCEPTION_500_9000 = new WebappException(HttpStatus.INTERNAL_SERVER_ERROR, 9000, "Could not store file %s. Please try again!", "","");
	
	//Contact
	public static WebappException CONTACT_ALREADY_EXISTS_400_6003 = new WebappException(HttpStatus.BAD_REQUEST, 4000, "Contact already EXISTS.", "", "");
	
	//Contact
	public static WebappException TEAM_NOTFOUND_400_5000 = new WebappException(HttpStatus.BAD_REQUEST, 4000, "Team Not Found.", "", "");
	
	//Team
	public static  WebappException TEAM_IMAGETYPENOTFOUND_400_5001 = new WebappException(HttpStatus.BAD_REQUEST, 5000, "Team images not found", "", "");
	public static  WebappException TEAM_NOTFOUND_400_6000 = new WebappException(HttpStatus.BAD_REQUEST, 5000, "Team id not found", "", "");
}
