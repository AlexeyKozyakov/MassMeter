package com.dacker.adouble.massmeter.ref;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addCategory(Category category);
    @Insert
    public void addFigure(Figure figure);
    @Insert
    public void addMaterial(Material material);


    @Delete
    public void deleteCategory(Category category);
    @Delete
    public void deleteFigure(Figure figure);
    @Delete
    public void deleteMaterial(Material material);

    ///////////////  ЗАПРОСЫ   \\\\\\\\\\\\\\\\\\

    @Query("SELECT * FROM Categories")
    public List<Category> getAllCategories();                                                                                            // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ

    @Query("SELECT * FROM Categories WHERE type = 'figure'")                                                                             // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ ФИГУР
    public List<Category> getFigureCategories();

    @Query("SELECT * FROM Categories WHERE type = 'material'")                                                                           // ВОЗВРАЩАЕТ СПИСОК ВСЕХ КАТЕГОРИЙ МАТЕРИАЛОВ
    public List<Category> getMaterialCategories();

    @Query("SELECT * FROM Categories INNER JOIN Materials ON Categories.id = Materials.cat_id WHERE Materials.cat_id = :category")       // ВОЗВРАЩАЕТ СПИСОК МАТЕРИАЛОВ ПО ID КАТЕГОРИИ(ПРИНАДЛЕЖАЩИХ ЭТОЙ КАТЕГОРИИ)
    public List<Material> getMaterialsFromCategory(int category);

    @Query("SELECT * FROM Categories INNER JOIN Figures ON Categories.id = Figures.cat_id WHERE Figures.cat_id = :category")             // ВОЗВРАЩАЕТ СПИСОК ФИГУР ПО ID КАТЕГОРИИ(ПРИНАДЛЕЖАЩИХ ЭТОЙ КАТЕГОРИИ)
    public List<Figure> getFiguresFromCategory(int category);

    @Query("SELECT * FROM Figures")                                                                                                      // ВОЗВРАЩАЕТ СПИСОК ВСЕХ ФИГУР
    public List<Figure> getAllFigures();

    @Query("SELECT * FROM Materials")                                                                                                    // ВОЗВРАЩАЕТ СПИСОК ВСЕХ МАТЕРИАЛОВ
    public List<Material> getAllMaterialss();

    @Query("SELECT * FROM Materials WHERE id = :id")                                                                                     // ВОЗВРАЩАЕТ МАТЕРИАЛ ПО ID
    public Material getMaterialById(int id);

    @Query("SELECT * FROM FIGURES WHERE id = :id")                                                                                       // ВОЗВРАЩАЕТ ФИГУРУ ПО ID
    public Figure getFigureById(int id);
}
