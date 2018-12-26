package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "MaterialInfo")
public class MaterialInfo {

    @PrimaryKey
    private int materialid;

<<<<<<< HEAD
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "image")
    private String reference;

    public MaterialInfo(int materialid, byte[] image, String reference) {
        this.materialid = materialid;
        this.image = image;
        this.reference = reference;
=======
    @ColumnInfo(name = "materialImage",typeAffinity = ColumnInfo.BLOB)
    private byte[] materialImage;

    @ColumnInfo(name = "materialReference")
    private String materialReference;

    public MaterialInfo(int materialid, byte[] materialImage, String materialReference) {
        this.materialid = materialid;
        this.materialImage = materialImage;
        this.materialReference = materialReference;
>>>>>>> origin/master
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/master
    }

    /*    public static MaterialInfo[] populateData(){
        return new MaterialInfo[]{
                new MaterialInfo(,,);
        }
    }
   */

}

