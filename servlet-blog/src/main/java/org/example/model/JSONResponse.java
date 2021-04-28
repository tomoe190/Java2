package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONResponse {
    // 标识业务操作是否成功
    private boolean success;
    // 业务数据
    private Object data;
    // 错误码
    private String code;
    // 错误信息
    private String message;

}
