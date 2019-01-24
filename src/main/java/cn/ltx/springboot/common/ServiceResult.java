package cn.ltx.springboot.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Description: 定义service层的Result。在controller层只会作一些简单的参数校验，在service层会作进一步的校验，如果controller中需要统一返回一个JsonResult可以将ServiceResult作一个简单的转换器进行转换。
 *
 * @author litianxiang
 * @date 2019/1/24
 */
public class ServiceResult<T, C> implements Serializable {

    public static final CodeMessage<String> SUCCESS = new DefaultMessage<>("00000000", "success");

    private T data;
    private CodeMessage<C> message;
    private boolean isSuccess;

    ServiceResult(T data, boolean isSuccess, CodeMessage<C> message) {
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public T getData() {
        return data;
    }

    public CodeMessage<C> getCodeMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public static <D, C> ServiceResultBuilder<D, C> success() {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(true);
    }

    public static <D, C> ServiceResultBuilder<D, C> success(CodeMessage<C> codeMessage) {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(true).code(codeMessage.getCode()).message(codeMessage.getMessage());
    }

    public static <D> ServiceResult<D, String> success(D data) {
        ServiceResultBuilder<D, String> success = success(SUCCESS);
        return success.data(data).build();
    }

    public static <D, C> ServiceResult<D, C> error(CodeMessage<C> codeMessage) {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(false).code(codeMessage.getCode()).message(codeMessage.getMessage()).build();
    }

    public static <D, C> ServiceResultBuilder<D, C> error() {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(false);
    }


    static <D, C> ServiceResultBuilder<D, C> builder() {
        return new ServiceResultBuilder<>();
    }

    public static class DefaultMessage<C> implements CodeMessage<C>, java.io.Serializable {

        private C code;
        private String message;

        public DefaultMessage(C code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public C getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "DefaultMessage{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }


    public static class ServiceResultBuilder<T, C> {

        private T data;
        private C code;
        private String message;
        private boolean isSuccess;

        ServiceResultBuilder() { //package private
        }

        public ServiceResultBuilder<T, C> data(T data) {
            this.data = data;
            return this;
        }

        ServiceResultBuilder<T, C> isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public ServiceResultBuilder<T, C> code(C code) {
            this.code = code;
            return this;
        }

        public ServiceResultBuilder<T, C> message(String message) {
            this.message = message;
            return this;
        }

        public ServiceResult<T, C> build() {
            Objects.requireNonNull(code, "code");
            Objects.requireNonNull(code, "message");
            return new ServiceResult<>(data, isSuccess, new DefaultMessage<>(code, message));
        }
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "data=" + data +
                ", message=" + message +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public static void main(String[] args) {
        //返回正确结果带返回值。
        ServiceResult<List<String>, String> s = ServiceResult.success(Arrays.asList("1", "2", "3"));
        s.isSuccess(); //true
        ServiceResult<?, Integer> e1 = ServiceResult.error(CodeMessageEnum.some_error);
        e1.isSuccess(); //false
        ServiceResult<?, Long> e2 = ServiceResult.error(new ServiceResult.DefaultMessage<>(1000L, "error"));
        e2.isSuccess(); //false
        System.out.println(s);
        System.out.println(e1);
        System.out.println(e2);
    }
}