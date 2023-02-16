/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author hanin
 */
public interface IService<T> {
    void insert(T t);
    void delete(int id);
    void update(List<Object> list,int id);
    List <T> readAll();
    T readByID(int id);
    
}