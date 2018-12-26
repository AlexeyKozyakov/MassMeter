package com.dacker.adouble.massmeter.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ReferenceDao {

    @Insert
    public void addCategory(Category category);
    @Insert
    public void addFigure(Figure figure);
    @Insert
    public void addMaterial(Material material);
    @Insert
    public void addPreset(Preset preset);
    @Insert
    public void addInfoFigure(FigureInfo info);
    @Insert
    public void addInfoMaterial(MaterialInfo info);


    @Insert
    void insertAllCategories(Category... categories);
    @Insert
    void insertAllFigures(Figure... categories);
    @Insert
    void insertAllMaterials(Material... categories);
    @Insert
    void insertAllMaterialINFO(MaterialInfo... infos);
    @Insert
    void insertAllFigureINFO(FigureInfo... infos);



    @Delete
    public void deleteCategory(Category category);
    @Delete
    public void deletePreset(Preset preset);
    @Delete
    public void deleteFigure(Figure figure);
    @Delete
    public void deleteMaterial(Material material);
    @Delete
    public void deleteInfoFigure(FigureInfo info);
    @Delete
    public void deleteInfoMaterial(MaterialInfo info);

    ///////////////  ЗАПРОСЫ   \\\\\\\\\\\\\\\\\\

    @Query("SELECT * FROM Categories")
    public List<Category> getAllCategories();                                                                                            // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ

    @Query("SELECT * FROM Categories WHERE type = 'figure'")                                                                             // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ ФИГУР
    public List<Category> getFigureCategories();

    @Query("SELECT * FROM Categories WHERE type = 'material'")                                                                           // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ МАТЕРИАЛОВ
    public List<Category> getMaterialCategories();

    @Query("SELECT * FROM Categories INNER JOIN Materials ON Categories.id = Materials.catId WHERE Materials.catId = :category")         // ВОЗВРАЩАЕТ СПИСОК МАТЕРИАЛОВ ПО ID КАТЕГОРИИ(ПРИНАДЛЕЖАЩИХ ЭТОЙ КАТЕГОРИИ)
    public List<Material> getMaterialsFromCategory(int category);

    @Query("SELECT * FROM Categories INNER JOIN Figures ON Categories.id = Figures.catId WHERE Figures.catId = :category")               // ВОЗВРАЩАЕТ СПИСОК ФИГУР ПО ID КАТЕГОРИИ(ПРИНАДЛЕЖАЩИХ ЭТОЙ КАТЕГОРИИ)
    public List<Figure> getFiguresFromCategory(int category);

    @Query("SELECT * FROM Figures")                                                                                                      // ВОЗВРАЩАЕТ СПИСОК ВСЕХ ФИГУР
    public List<Figure> getAllFigures();

    @Query("SELECT * FROM Materials")                                                                                                    // ВОЗВРАЩАЕТ СПИСОК ВСЕХ МАТЕРИАЛОВ
    public List<Material> getAllMaterialss();

    @Query("SELECT * FROM Materials WHERE id = :id")                                                                                     // ВОЗВРАЩАЕТ МАТЕРИАЛ ПО ID
    public Material getMaterialById(int id);

    @Query("SELECT * FROM FIGURES WHERE id = :id")                                                                                       // ВОЗВРАЩАЕТ ФИГУРУ ПО ID
    public Figure getFigureById(int id);

    @Query("SELECT * FROM Presets WHERE Presets.id = :id")                                                                              // ВОЗВРАЩАЕТ ПРЕСЕТ ПО ID ПРЕСЕТА
    public Preset getPresetById(int id);

    @Query("SELECT * FROM Presets")                                                                                                     // ВОЗВРАЩАЕТ СПИСОК ВСЕХ ПРЕСЕТОВ
    public List<Preset> getAllPresets();

    @Query("SELECT * FROM FigureInfo WHERE figureid = :id")                                                                             // КАРТИНОЧКУ И ОПИСАНИЕ ФИГУРЫ ПО ID
    public FigureInfo getFigureInfo(int id);

    @Query("SELECT * FROM MaterialInfo WHERE materialid = :id")                                                                         // КАРТИНОЧКУ И ОПИСАНИЕ МАТЕРИАЛА ПО ID
    public MaterialInfo getMaterialInfo(int id);
}