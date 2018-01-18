package com.hedyhidoury.githubprofile.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.hedyhidoury.githubprofile.data.local.setup.RoomDatabasSetuper;
import com.hedyhidoury.githubprofile.data.local.daos.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

@Module
public abstract class RoomModule {

    @Singleton
    @Provides
    public static RoomDatabasSetuper provideRoomDataBase(Application application){
        return Room.databaseBuilder(application, RoomDatabasSetuper.class, "Githublocaldb").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public static UserDao provideUserDao(RoomDatabasSetuper roomDatabasSetuper){
        return roomDatabasSetuper.userDao();
    }
}
