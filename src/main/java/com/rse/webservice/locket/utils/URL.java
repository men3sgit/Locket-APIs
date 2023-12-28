package com.rse.webservice.locket.utils;

public interface URL {
    String DOMAIN = "http://localhost:8000";
    String API_V1_VERIFY_PATH = "/api/v1/verify";

    String REGISTRATION_CONFIRM = "/registration-confirm";
    String RESEND_TOKEN = "/resend-token";


    String NEW_USER_VERIFICATION_URL = DOMAIN + API_V1_VERIFY_PATH + REGISTRATION_CONFIRM;
}
