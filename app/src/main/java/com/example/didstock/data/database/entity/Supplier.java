package com.example.didstock.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="supplier")
public class Supplier {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String name;
    private String address;
    @ColumnInfo(name = "logo_uri")
    private String logoUri;

    public Supplier(Integer id, String name, String address, String logoUri) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.logoUri = logoUri;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }
}
