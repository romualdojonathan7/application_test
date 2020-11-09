package br.com.jonathan.challenge.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.jonathan.challenge.model.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("select * from tb_user user where user.in_user_id = :userId")
    LiveData<User> getUser(Integer userId);

    @Query("select * from tb_user user where user.te_email = :email and user.te_password = :password")
    User findByEmailAndPassword(String email, String password);
}
