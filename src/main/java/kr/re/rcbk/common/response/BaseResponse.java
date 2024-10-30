package kr.re.rcbk.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BaseResponse<T> {

    private final DataHeader dataHeader;
    private final T data;
    private final T error;

    public BaseResponse(DataHeader dataHeader, T data, T error) {
        this.dataHeader = dataHeader;
        this.data = data;
        this.error = error;
    }

    public static <T> BaseResponse<?> ok(T data) {
        return new BaseResponse<>(DataHeader.ok(), data, null);
    }

    public static BaseResponse<?> error(HttpStatus status, Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message",e.getMessage());
        return new BaseResponse<>(DataHeader.error(status), null, errors);
    }

    public static BaseResponse<?> error(HttpStatus status, String message) {
        return new BaseResponse<>(DataHeader.error(status), null, message);
    }
}

