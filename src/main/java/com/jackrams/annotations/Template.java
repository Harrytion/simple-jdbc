package com.jackrams.annotations;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Template {
   public boolean isSQL()  default true;
   public String value() default "";

}
