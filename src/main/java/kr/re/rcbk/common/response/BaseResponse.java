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
        Map<String, String> errors = new HashMap<>();
        errors.put("isError", Boolean.FALSE.toString());
        return new BaseResponse<>(DataHeader.ok(), data, errors);
    }

    public static BaseResponse<?> error(HttpStatus status, Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("code", Integer.toString(status.value()));
        errors.put("message",e.getMessage());
        errors.put("isError", Boolean.toString(status.isError()));
        return new BaseResponse<>(DataHeader.error(status), null, errors);
    }

    public static BaseResponse<?> error(HttpStatus status, String message) {
        Map<String, String> errors = new HashMap<>();
        errors.put("code", Integer.toString(status.value()));
        errors.put("message",message);
        errors.put("isError", Boolean.toString(status.isError()));
        return new BaseResponse<>(DataHeader.error(status), null, errors);
    }
}

