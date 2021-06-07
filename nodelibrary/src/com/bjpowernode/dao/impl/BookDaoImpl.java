package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.dao.UserDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author wuyuecai
 * @create 2021/6/7
 */
public class BookDaoImpl implements BookDao {
    /**
     * 从文件中读取list数据，通用操作
     */
    public List<Book> read(){
        ObjectInputStream ois = null;
        //1.读取文件中的list
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }
    /**
     * 将list写入到文件中的通用操作
     */
    public List<Book> write(List<Book> list){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<>();
    }
    /**
     * 添加
     * @param book
     * @return
     */
    @Override
    public List<Book> select(Book book) {
        ObjectInputStream ois = null;
        //1.从文件中读取list
        try {
             ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null){//当booklist不为空时，进行查询，包括全查询和条件查询
                if(book == null ||("".equals(book.getBookName()) && "".equals(book.getIsbn()))){
                    return list;
                }
                List<Book> list1 = new ArrayList<>();//用于接收查询出来的图书对象
                //判断一个条件的查询
                if(!"".equals(book.getBookName())){//使用lambda表达式进行查询
                    List<Book> collect = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                    return collect;
                }
                if(!"".equals(book.getIsbn())){
                    List<Book> collect = list.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    return collect;
                }
                //判断两个条件的查询
                if(!"".equals(book.getBookName()) && !"".equals(book.getIsbn())){
                    List<Book> collect = list.stream().filter(b -> (b.getBookName().equals(book.getBookName()) && (b.getIsbn().equals(book.getIsbn())))).collect(Collectors.toList());
                    return collect;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //如果list为空，返回一个空集合
        return new ArrayList<>();
    }
    /*
    添加
     */
    @Override
    public void add(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        //1.读取文件中的list
        try {
             ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            //2.根据list中最后一个对象的ID设置book对象的ID，然后再设置图书状态为入库
            if(list != null) {
                Book book1 = list.get(list.size() - 1);
                book.setId(book1.getId()+1);
                book.setStatus(Constant.STATUS_STORAGE);
                list.add(book);
            }else{
                book.setId(1);
                book.setStatus(Constant.STATUS_STORAGE);
                list.add(book);
            }
            //3.将list写出到文件中
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
            oos.writeObject(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加失败");
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /*
    删除
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        //1.读取文件中的list
        List<Book> list = read();
        //2.通过ID查找要删除的book对象
        Book book = list.stream().filter(b -> b.getId() == id).findFirst().get();
        list.remove(book);
        //3.将list写出到文件中
        write(list);

    }
    /*
    修改
     */
    @Override
    public void update(Book book) {
        //1.读取文件中list的数据
        List<Book> list = read();
        //2.根据ID查找list中的book对象，然后进行修改数据
        Book book1 = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();
        book1.setBookName(book.getBookName());
        book1.setIsbn(book.getIsbn());
        book1.setAuthor(book.getAuthor());
        book1.setPublisher(book.getPublisher());
        book1.setType(book.getType());
        //3.将list写出到文件中
        write(list);

    }
}
