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

    public MaterialInfo(int materialid, String materialImage, String materialReference) {
        this.materialid = materialid;
        this.materialImage = materialImage;
        this.materialReference = materialReference;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public byte[] getMaterialImage() {
        return materialImage;
    }

    public void setMaterialImage(byte[] materialImage) {
        this.materialImage = materialImage;
    }

    public String getMaterialReference() {
        return materialReference;
    }

    public void setMaterialReference(String materialReference) {
        this.materialReference = materialReference;
    }

        public static MaterialInfo[] populateData(Context context){
            try {
                CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("material_info.csv")));
                List<MaterialInfo> materialInfos = new ArrayList<>();
                String [] row;
                while ((row = reader.readNext()) != null) {
                    materialInfos.add(new MaterialInfo(Integer.valueOf(row[0]), row[1], row[2])));
                }
                return materialList.toArray(new Material[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }

}

