package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FigureInfo")
public class FigureInfo {

    @PrimaryKey
    private int figureid;

<<<<<<< HEAD
    @ColumnInfo(name = "image",typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "image")
    private String reference;

    public FigureInfo(int figureid, byte[] image, String reference) {
        this.figureid = figureid;
        this.image = image;
        this.reference = reference;
=======
    @ColumnInfo(name = "figureImage",typeAffinity = ColumnInfo.BLOB)
    private byte[] figureImage;

    @ColumnInfo(name = "figureReference")
    private String figureReference;

    public FigureInfo(int figureid, byte[] figureImage, String figureReference) {
        this.figureid = figureid;
        this.figureImage = figureImage;
        this.figureReference = figureReference;
>>>>>>> origin/master
    }

    public int getFigureid() {
        return figureid;
    }

    public void setFigureid(int figureid) {
        this.figureid = figureid;
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
    public byte[] getFigureImage() {
        return figureImage;
    }

    public void setFigureImage(byte[] figureImage) {
        this.figureImage = figureImage;
    }

    public String getFigureReference() {
        return figureReference;
    }

    public void setFigureReference(String figureReference) {
        this.figureReference = figureReference;
>>>>>>> origin/master
    }


/*    public static FigureInfo[] populateData(){
        return new FigureInfo[]{
                new FigureInfo(,,);
        }
    }
   */
}

