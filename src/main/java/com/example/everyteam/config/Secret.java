package com.example.everyteam.config;

public interface Secret{
	String SECRET = "asldfjalsdkfjlksjaglkjlkasjdflkjlaskjdflkajsldgkjlk"; // 우리 서버만 알고 있는 비밀값
	int AC_EXPIRATION_TIME = 3*60*60*1000; // 60분 (1/1000초)
	long REF_EXPIRATION_TIME = 7*24*60*60*1000L;//토큰 유효시간 7일
	int EMAIL_EXPIRATION_TIME = 5*60*1000;
	String TOKEN_PREFIX = "Bearer ";
	String ACCESS_HEADER_STRING = "X-AUTH-TOKEN";
	String REFRESH_HEADER_STRING = "X-AUTH-REF-TOKEN";

	public static String buketName = "weave_bucket";
	public static String keyFileName = "civil-treat-360317-cea5f2d8ef7c.json";
	public static String projectId = "civil-treat-360317";
	public static String resUrl = "https://storage.googleapis.com/weave_bucket/";
}
