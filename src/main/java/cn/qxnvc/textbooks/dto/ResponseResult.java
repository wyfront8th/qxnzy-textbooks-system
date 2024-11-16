package cn.qxnvc.textbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    //状态码
    private Integer code;
    //消息
    private String msg;
    //数据
    private T data;
    //成功返回
    public static <T>ResponseResult<T> success(ResultCodeEnum resultCodeEnum){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }
    public static <T>ResponseResult<T> success(ResultCodeEnum resultCodeEnum, T data){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        result.setData(data);
        return result;
    }

    public static <T>ResponseResult<T> fail(ResultCodeEnum resultCodeEnum){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    public static <T>ResponseResult<T> fail(ResultCodeEnum resultCodeEnum,T data){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        result.setData(data);
        return result;
    }
    public static <T>ResponseResult<T> fail(Integer code,String message,T data){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

}
