package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "MaterialInfo")
public class MaterialInfo {

    @PrimaryKey
    private int materialid;

    @ColumnInfo(name = "materialImage",typeAffinity = ColumnInfo.BLOB)
    private byte[] materialImage;

    @ColumnInfo(name = "materialReference")
    private String materialReference;

    public MaterialInfo(int materialid, byte[] materialImage, String materialReference) {
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

    /*    public static MaterialInfo[] populateData(){
        return new MaterialInfo[]{
                new MaterialInfo(,,);
        }
    }
   */

}

