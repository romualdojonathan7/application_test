package br.com.jonathan.challenge.model;

import java.sql.Timestamp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity(tableName = "tb_user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "in_user_id")
    @NonNull
    private Integer id;

    @ColumnInfo(name = "te_fullname")
    private String fullName;

    @NonNull
    @ColumnInfo(name = "te_email")
    private String email;

    @NonNull
    @ColumnInfo(name = "te_password")
    private String password;

    @ColumnInfo(name = "dt_created_at")
    private Timestamp createdAt;

    @ColumnInfo(name = "dt_updated_at")
    private Timestamp updatedAt;

    @Ignore
    public User(String fullName, String email, String password){
        this.fullName = fullName;
        this.email = email;
        this.password =  password;
    }
}
