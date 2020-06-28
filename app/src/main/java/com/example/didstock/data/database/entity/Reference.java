package com.example.didstock.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "reference",
        foreignKeys = @ForeignKey(
                entity = Supplier.class,
                parentColumns = "id",
                childColumns = "supplierId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Reference {

    @PrimaryKey
    @NonNull
    private String code;
    @ColumnInfo(name = "image_uri")
    private String imageUri;
    private String description;
    private Integer supplierId;

    public Reference(@NonNull String code, String imageUri, String description, Integer supplierId) {
        this.code = code;
        this.imageUri = imageUri;
        this.description = description;
        this.supplierId = supplierId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String image_uri) {
        this.imageUri = image_uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
