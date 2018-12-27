package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "MaterialInfo")
public class MaterialInfo {

    @PrimaryKey
    private int materialid;

    @ColumnInfo(name = "materialImage")
    private String materialImage;

    @ColumnInfo(name = "materialReference")
    private String materialReference;

    @ColumnInfo(name = "builtIn")
    private boolean builtIn;

    public MaterialInfo(int materialid, String materialImage, String materialReference, boolean builtIn) {
        this.materialid = materialid;
        this.materialImage = materialImage;
        this.materialReference = materialReference;
        this.builtIn = builtIn;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public String getMaterialImage() {
        return materialImage;
    }

    public void setMaterialImage(String materialImage) {
        this.materialImage = materialImage;
    }

    public String getMaterialReference() {
        return materialReference;
    }

    public void setMaterialReference(String materialReference) {
        this.materialReference = materialReference;
    }

    public boolean getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(boolean builtIn) {
        this.builtIn = builtIn;
    }

    public static MaterialInfo[] populateData(Context context){
            try {
                CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("material_info.csv")));
                List<MaterialInfo> materialInfos = new ArrayList<>();
                String [] row;
                while ((row = reader.readNext()) != null) {
                    materialInfos.add(new MaterialInfo(Integer.valueOf(row[0]), row[1], row[2], Boolean.valueOf(row[3])));
                }
                return materialInfos.toArray(new MaterialInfo[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

}

