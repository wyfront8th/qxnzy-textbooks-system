package cn.qxnvc.textbooks.dto;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"操作成功"),
    ERROR(500,"系统内部错误"),
    DATANULL(600,"数据不存在");
    private final Integer code;
    private final String message;
    ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
