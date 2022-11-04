package fun.pigeondog.common.annotation;

import java.lang.annotation.*;

/**
 * CommonResultAdvice
 *
 * @author yzguo
 * @date 2022/11/4 0:18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonResultAdvice {

    boolean value() default true;
}
