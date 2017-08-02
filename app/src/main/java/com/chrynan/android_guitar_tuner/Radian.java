package com.chrynan.android_guitar_tuner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation identifier to indicate that a particular value is in radians. No check is done to
 * determine if this annotation is properly used. It is mainly used for better clarity in
 * distinguishing between radian and angle fields.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
public @interface Radian {
}
