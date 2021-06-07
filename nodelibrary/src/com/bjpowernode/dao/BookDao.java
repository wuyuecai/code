package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public interface BookDao {
    List<Book> select(Book book);//��ѯ
    void add(Book book);//���
    void delete(int id);//ɾ��
    void update(Book book);//�޸�
}
