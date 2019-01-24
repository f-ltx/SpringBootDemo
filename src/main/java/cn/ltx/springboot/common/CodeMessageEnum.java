package cn.ltx.springboot.common;

/**
 * Description: 定义枚举错误码
 * @author      litianxiang
 * @date        2019/1/24
 */
public enum CodeMessageEnum implements CodeMessage<Integer> {
   some_error(1001, "错误信息。");
   Integer code;
   String message;

   CodeMessageEnum(Integer code, String message) {
       this.code = code;
       this.message = message;
   }

   @Override
   public Integer getCode() {
       return code;
   }

   @Override
   public String getMessage() {
       return message;
   }
}