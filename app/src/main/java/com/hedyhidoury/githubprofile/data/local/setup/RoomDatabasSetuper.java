package com.hedyhidoury.githubprofile.data.local.setup;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hedyhidoury.githubprofile.data.local.daos.UserDao;
import com.hedyhidoury.githubprofile.data.models.UserModel;

import static com.hedyhidoury.githubprofile.data.local.setup.RoomDatabasSetuper.VERSION;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

@Database(entities = {UserModel.class}, version = VERSION)
public abstract class RoomDatabasSetuper extends RoomDatabase {

    static final int VERSION = 1;

    abstract public UserDao userDao();
}
