package com.bjpowernode.service;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public interface BookService {

    void add(Book book) ;//���
    List<Book> select(Book book);//��ѯ
    void delete(int id);//ɾ��
    void update(Book book);//�޸�
}
