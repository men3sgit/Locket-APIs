package com.rse.webservice.locket.payload;

import com.rse.webservice.locket.constants.HttpStatusCode;
import com.rse.webservice.locket.constants.ConstantKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

import static com.rse.webservice.locket.utils.RequestUtils.getRequestUrl;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private Instant instant;
    private String url;

    public ApiResponse(T data) {
        this.data = data;
        this.code = HttpStatusCode.OK;
        this.message = ConstantKey.MSG_SUCCESS;
        this.instant = Instant.now();
        this.url = getRequestUrl();
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", time=" + instant +        // modified
                ", url='" + url + '\'' +
                '}';
    }
}
