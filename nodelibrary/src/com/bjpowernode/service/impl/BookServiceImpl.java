package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.BookDaoImpl;
import com.bjpowernode.service.BookService;

import java.util.List;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    /*
    ²éÑ¯
     */
    @Override
    public List<Book> select(Book book) {
        return bookDao.select(book);
    }
    /*
    Ìí¼Ó
     */
    @Override
    public void add(Book book) {
        bookDao.add(book);
    }
    /*
    É¾³ý
     */
    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }
    /*
    ÐÞ¸Ä
     */
    @Override
    public void update(Book book) {
        bookDao.update(book);
    }
}
