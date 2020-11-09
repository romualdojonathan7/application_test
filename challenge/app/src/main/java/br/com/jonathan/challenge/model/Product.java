package br.com.jonathan.challenge.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Timestamp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import br.com.jonathan.challenge.model.converter.TimestampConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(tableName = "tb_product")
public class Product {

    @PrimaryKey
    @NonNull
    @SerializedName(value = "id")
    private Integer id;

    @SerializedName(value = "brand")
    private String brand;

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "image_link")
    private String image;

    @SerializedName(value = "product_type")
    private String productType;

    @SerializedName(value = "created_at")
    private Timestamp createdAt;

    @SerializedName(value = "updated_at")
    private Timestamp updatedAt;

    @SerializedName(value = "description")
    private String description;
}
