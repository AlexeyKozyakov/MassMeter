package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FigureInfo")
public class FigureInfo {

    @PrimaryKey
    private int figureid;

    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "image")
    private String reference;

    public FigureInfo(int figureid, byte[] image, String reference) {
        this.figureid = figureid;
        this.image = image;
        this.reference = reference;
    }

    public int getFigureid() {
        return figureid;
    }

    public void setFigureid(int figureid) {
        this.figureid = figureid;
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


/*    public static FigureInfo[] populateData(){
        return new FigureInfo[]{
                new FigureInfo(,,);
        }
    }
   */
}

