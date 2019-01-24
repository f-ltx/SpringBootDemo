package cn.ltx.springboot.common;

/**
 * Description: 定义消息接口
 * @author      litianxiang
 * @date        2019/1/24
 */
public interface CodeMessage<C>{
    C getCode();
    String getMessage();
}