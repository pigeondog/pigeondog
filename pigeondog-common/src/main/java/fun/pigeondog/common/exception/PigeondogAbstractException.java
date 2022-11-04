package fun.pigeondog.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * AbstractBlogException
 *
 * @author yzguo
 * @date 2022/10/16 13:52
 */
public abstract class PigeondogAbstractException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public PigeondogAbstractException(String message) {
        super(message);
    }

    public PigeondogAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public PigeondogAbstractException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
