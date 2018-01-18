package com.hedyhidoury.githubprofile.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AuthScope {
}
