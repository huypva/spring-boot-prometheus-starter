package io.codebyexample.monitorstarter.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huypva
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Metric {

  String value() default "";

  String[] extraTags() default {};

  boolean histogram() default false;

  String description() default "";
}