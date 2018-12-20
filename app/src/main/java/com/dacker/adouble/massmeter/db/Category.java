package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Categories")
public class Category {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private String type;

    public Category(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Category[] populateData() {
        return new Category[] {
                new Category(0, "Common", "figure"),
                new Category(1, "Polyhedra", "figure"),
                new Category(5, "User", "figure"),
                new Category(2, "Wood", "material"),
                new Category(3, "Metal", "material"),
                new Category(4, "Plastic", "material")
        };
    }
}
