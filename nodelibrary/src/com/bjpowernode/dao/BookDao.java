package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public interface BookDao {
    List<Book> select(Book book);//²éÑ¯
    void add(Book book);//Ìí¼Ó
    void delete(int id);//É¾³ý
    void update(Book book);//ÐÞ¸Ä
}
