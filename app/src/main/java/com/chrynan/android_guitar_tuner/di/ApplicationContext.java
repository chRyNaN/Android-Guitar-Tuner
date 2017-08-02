package com.chrynan.android_guitar_tuner.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * An annotation {@link Qualifier} that indicates when we are pointing to the Application's
 * {@link android.content.Context}.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
