package br.com.jonathan.challenge.database.dao;

import java.sql.Timestamp;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import br.com.jonathan.challenge.model.Product;

@Dao
public abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(Product product);

    public void insertWithDate(Product product) {
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        insert(product);
    }

    @Update
    abstract public void update(Product product);

    public void updateWithDate(Product user){
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        update(user);
    }

    @Delete
    public abstract void delete(Product product);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertAll(List<Product> products);

    public void insertAllWithDate(List<Product> products) {
        if (products != null) {
            for (Product product : products) {
                product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            }
        }
        insertAll(products);
    }

    @Query("select * from tb_product order by createdAt ASC")
    public abstract LiveData<List<Product>> getAllProducts();
}
