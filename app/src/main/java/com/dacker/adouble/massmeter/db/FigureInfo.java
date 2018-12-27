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

@Entity(tableName = "FigureInfo")
public class FigureInfo {

    @PrimaryKey
    private int figureid;

    @ColumnInfo(name = "figureImage")
    private String figureImage;

    @ColumnInfo(name = "figureReference")
    private String figureReference;

    @ColumnInfo(name = "builtIn")
    private boolean builtIn;

    public FigureInfo(int figureid, String figureImage, String figureReference, boolean builtIn) {
        this.figureid = figureid;
        this.figureImage = figureImage;
        this.figureReference = figureReference;
        this.builtIn = builtIn;
    }

    public int getFigureid() {
        return figureid;
    }

    public void setFigureid(int figureid) {
        this.figureid = figureid;
    }

    public String getFigureImage() {
        return figureImage;
    }

    public void setFigureImage(String figureImage) {
        this.figureImage = figureImage;
    }

    public String getFigureReference() {
        return figureReference;
    }

    public void setFigureReference(String figureReference) {
        this.figureReference = figureReference;
    }

    public boolean getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(boolean builtIn) {
        this.builtIn = builtIn;
    }

    public static FigureInfo[] populateData(Context context){
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("figure_info.csv")));
            List<FigureInfo> figureInfoList = new ArrayList<>();
            String [] row;
            while ((row = reader.readNext()) != null) {
                figureInfoList.add(new FigureInfo(Integer.valueOf(row[0]), row[1], row[2], Boolean.valueOf(row[3])));
            }
            return figureInfoList.toArray(new FigureInfo[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

