package br.com.jonathan.challenge.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.jonathan.challenge.database.AppDatabase;
import br.com.jonathan.challenge.database.AppExecutor;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.model.User;

public class UserRepository {

    private final AppDatabase appDatabase;
    private static UserRepository instance;

    UserRepository(final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public static UserRepository getInstance(final AppDatabase appDatabase){
        if(instance == null) {
            instance = new UserRepository(appDatabase);
        }

        return instance;
    }

    public void insert(final User user) {
        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao().insert(user);
            }
        });
    }

    public LiveData<User> getUser(Integer userId){
        return appDatabase.userDao().getUser(userId);
    }

    public User findByEmailAndPassword(final String email, final String password){
         return appDatabase.userDao().findByEmailAndPassword(email, password);
    }
}
