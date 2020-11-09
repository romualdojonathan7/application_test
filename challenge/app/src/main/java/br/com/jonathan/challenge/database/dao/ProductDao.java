package br.com.jonathan.challenge.database.dao;

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
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Product> products);

    @Query("select * from tb_product order by createdAt ASC")
    LiveData<List<Product>> getAllJobs();
}
