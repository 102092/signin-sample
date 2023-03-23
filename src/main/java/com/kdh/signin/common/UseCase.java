package com.kdh.signin.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author han
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
}
