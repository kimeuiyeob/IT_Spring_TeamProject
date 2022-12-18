/*
 * 황지수
 * */

package com.app.milestone.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션을 만들어주었다.(아직은 구현되지않았다.)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Alarm {;}
