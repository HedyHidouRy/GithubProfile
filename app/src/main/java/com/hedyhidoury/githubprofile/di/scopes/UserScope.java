package com.hedyhidoury.githubprofile.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserScope {
}
