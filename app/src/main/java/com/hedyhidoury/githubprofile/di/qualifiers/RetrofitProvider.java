package com.hedyhidoury.githubprofile.di.qualifiers;

import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrofitProvider {
    RetrofitType value();
}
