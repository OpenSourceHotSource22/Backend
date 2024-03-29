package com.example.everyteam.config.exception;

public enum ErrorResponseStatus {
    // 2000 : Request 오류
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    FAILED_TO_LOGOUT_JWT(false,2004,"로그아웃 실패하였습니다"),
    FAILED_TO_LOGIN_JWT(false,2004,"token을 확인하세요."),


    // 3000 : Response 오류
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),



    //4000 : Database, Server 오류
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    FILE_SAVE_ERROR(false, 4001, "파일 저장이 실패하였습니다."),
    EXIST_USER(false, 4002, "이미 등록된 id가 있습니다."),
    NOT_FOUND_USER(false, 4003, "등록된 유저가 없습니다."),
    INVALID_PWD(false, 4004, "비밀번호가 올바르지 않습니다."),
    TEAM_CODE_ERROR(false, 4005, "잘못된 team code 입니다."),
    NOT_FOUND_USER_IN_TEAM(false, 4006, "team에 등록되지 않은 유저입니다."),
    EXIST_USER_IN_TEAM(false, 4007, "이미 team에 등록된 유저입니다."),
    MEET_CODE_ERROR(false, 4008, "잘못된 meet code 입니다."),
    MEET_DATE_ERROR(false, 4009, "잘못된 날짜를 입력했습니다."),
    MEET_POST_ERROR(false, 4010, "Post에서 찾을 수 없는 MeetCode입니다."),
    MEET_USER_NOT_FOUND(false, 4011, "Meet에 등록되지 않은 유저입니다."),
    MEET_INVALID_USER(false, 4012, "Meet을 생성한 사람이 결과를 저장할 수 있습니다."),
    INVALID_COLOR(false, 4013, "잘못된 color를 요청했습니다."),
    EXIST_EMAIL(false, 4014, "이미 등록된 이메일 입니다."),


    //5000 : Server connection 오류
    SERVER_ERROR(false, 5000, "서버와의 연결에 실패하였습니다."),

    ;

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ErrorResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}