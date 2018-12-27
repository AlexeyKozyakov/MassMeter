package com.dacker.adouble.massmeter.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.dacker.adouble.massmeter.db.Figure;
import com.dacker.adouble.massmeter.db.FigureInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class AssetLoader {

    public static String saveImageToInternalStorage(Bitmap bitmapImage, String name, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory, name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public static Bitmap loadImageForFigure(Context context, FigureInfo info) {
        if (info.getBuiltIn()) {
            return loadImageFromAssets(context, info.getFigureImage());
        } else {
            return loadImageFromInternalStorage(info.getFigureImage());
        }
    }

    public static Bitmap loadImageFromInternalStorage(String path) {
        try {
            File f=new File(path);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap loadImageFromAssets(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(istr);
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    public static String loadStringFromAssets(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        StringBuilder text = new StringBuilder();
        try {
            istr = assetManager.open(path);
            Scanner scanner = new Scanner(istr);
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static String loadStringFromInternalStorage(String path) {
        File f=new File(path);
        InputStream istr;
        StringBuilder text = new StringBuilder();
        try {
            istr = new FileInputStream(path);
            Scanner scanner = new Scanner(istr);
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void saveStringToInternalStorage(String text, String name, Context context) {
        File directory = Environment.getDataDirectory();
        File mypath=new File(directory, name);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(mypath));
            writer.append(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
