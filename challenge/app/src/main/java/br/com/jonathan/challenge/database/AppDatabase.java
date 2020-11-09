package br.com.jonathan.challenge.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import br.com.jonathan.challenge.database.dao.ProductDao;
import br.com.jonathan.challenge.database.dao.UserDao;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.model.User;
import br.com.jonathan.challenge.model.converter.TimestampConverter;

@Database(entities = {
        User.class,
        Product.class
}, version = 1, exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static String DATABASE_NAME = "challenge.db";
    private static AppDatabase instance;

    // DAOs
    public abstract UserDao userDao();
    public abstract ProductDao productDao();

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return instance;
    }
}
