package rw.productant.v1.common.payloads;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponsePayload {
    public boolean success;
    public String message;

    public Object data;
    public ApiResponsePayload(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}