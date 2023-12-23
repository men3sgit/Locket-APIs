package com.rse.webservice.locket.constants;


public interface HttpStatusCode {
    int OK = 200;
    int CREATED = 201;
    int ACCEPTED = 202;
    int NO_CONTENT = 204;

    int BAD_REQUEST = 400;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int METHOD_NOT_ALLOWED = 405;

    int INTERNAL_SERVER_ERROR = 500;
    int NOT_IMPLEMENTED = 501;
    int BAD_GATEWAY = 502;
    int SERVICE_UNAVAILABLE = 503;
}
