package com.hedyhidoury.githubprofile.data.local.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hedyhidoury.githubprofile.data.models.UserModel;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    long insert(UserModel userModel);

    @Delete
    int delete(UserModel userModel);

    @Query("SELECT * FROM usermodel where id=:id")
    Flowable<UserModel> findUserById(long id);
}
