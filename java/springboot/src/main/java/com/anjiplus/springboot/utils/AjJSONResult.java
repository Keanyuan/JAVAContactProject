package com.anjiplus.springboot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/*
 *  @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */
public class AjJSONResult {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    private String ok;


    public static AjJSONResult build(Integer status, String msg, Object data) {
        return new AjJSONResult(status, msg, data);
    }

    public static AjJSONResult ok(Object data) {
        return new AjJSONResult(data);
    }

    public static AjJSONResult ok() {
        return new AjJSONResult(null);
    }

    public static AjJSONResult errorMsg(String msg) {
        return new AjJSONResult(500, msg, null);
    }

    public static AjJSONResult errorMap(Object data) {
        return new AjJSONResult(501, "error", data);
    }

    public static AjJSONResult errorTokenMsg(String msg) {
        return new AjJSONResult(502, msg, null);
    }

    public static AjJSONResult errorException(String msg) {
        return new AjJSONResult(555, msg, null);
    }

    public AjJSONResult() {
    }

    public AjJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public AjJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    /*
     *  将json结果集转化为LeeJSONResult对象
     * 				需要转换的对象是一个类
     */
    public static AjJSONResult formateToPojo(String jsonData, Class<?> classz) {

        try {
            if (classz == null) {
                return MAPPER.readValue(jsonData, AjJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (classz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), classz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), classz);
                }
            }
            return  build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (IOException e) {
            return  null;
        }
    }

    /*
    * 没有object对象的转化
    * */
    public static AjJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, AjJSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * Object是集合转化需要转换的对象是一个list
    * */
    public static AjJSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
