package com.hedyhidoury.githubprofile.base;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Created by Hedy HidouRy on 1/8/2018.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
