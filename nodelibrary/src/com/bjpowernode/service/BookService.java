package com.bjpowernode.service;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public interface BookService {

    void add(Book book) ;//Ìí¼Ó
    List<Book> select(Book book);//²éÑ¯
    void delete(int id);//É¾³ý
    void update(Book book);//ÐÞ¸Ä
}
