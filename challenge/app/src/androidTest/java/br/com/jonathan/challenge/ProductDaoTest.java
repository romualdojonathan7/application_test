package br.com.jonathan.challenge;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import br.com.jonathan.challenge.database.AppDatabase;
import br.com.jonathan.challenge.database.dao.ProductDao;
import br.com.jonathan.challenge.model.Product;

@RunWith(AndroidJUnit4.class)
public class ProductDaoTest {

    private AppDatabase appDatabase;

    private ProductDao productDao;

    @Before
    public void initDatabase() {
        appDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        productDao = appDatabase.productDao();
    }

    @Test
    public void getProducts() {
        List<Product> products = null;
    }

    @After
    public void closeDatabase() {
        appDatabase.close();
    }
}
