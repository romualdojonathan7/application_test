package br.com.jonathan.challenge.database.dao;

import java.sql.Timestamp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.jonathan.challenge.model.User;

@Dao
public abstract class UserDao {

    @Insert
    public abstract void insert(User user);

    public void insertWithDate(User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        insert(user);
    }

    @Update
    public abstract void update(User user);

    public void updateWithDate(User user){
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        update(user);
    }

    @Delete
    public abstract void delete(User user);

    @Query("select * from tb_user user where user.in_user_id = :userId")
    public abstract LiveData<User> getUser(Integer userId);

    @Query("select * from tb_user user where user.te_email = :email and user.te_password = :password")
    public abstract User findByEmailAndPassword(String email, String password);
}
