package com.dacker.adouble.massmeter.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FigureInfo")
public class FigureInfo {

    @PrimaryKey
    private int figureid;

    @ColumnInfo(name = "figureImage",typeAffinity = ColumnInfo.BLOB)
    private byte[] figureImage;

    @ColumnInfo(name = "figureReference")
    private String figureReference;

    public FigureInfo(int figureid, byte[] figureImage, String figureReference) {
        this.figureid = figureid;
        this.figureImage = figureImage;
        this.figureReference = figureReference;
    }

    public int getFigureid() {
        return figureid;
    }

    public void setFigureid(int figureid) {
        this.figureid = figureid;
    }

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
    }


/*    public static FigureInfo[] populateData(){
        return new FigureInfo[]{
                new FigureInfo(,,);
        }
    }
   */
}

