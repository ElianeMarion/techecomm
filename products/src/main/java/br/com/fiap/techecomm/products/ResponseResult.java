package br.com.fiap.techecomm.products;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private T result;
    private String message;
    public ResponseResult(T result, String message) {
        this.result = result;
        this.message = message;
    }
}
