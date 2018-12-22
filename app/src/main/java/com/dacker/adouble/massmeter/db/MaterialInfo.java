package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "MaterialInfo")
public class MaterialInfo {

    @PrimaryKey
    private int materialid;

    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "image")
    private String reference;

    public MaterialInfo(int materialid, byte[] image, String reference) {
        this.materialid = materialid;
        this.image = image;
        this.reference = reference;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    /*    public static MaterialInfo[] populateData(){
        return new MaterialInfo[]{
                new MaterialInfo(,,);
        }
    }
   */

}

