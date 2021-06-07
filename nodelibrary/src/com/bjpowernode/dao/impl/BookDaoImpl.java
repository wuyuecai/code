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
     * ���ļ��ж�ȡlist���ݣ�ͨ�ò���
     */
    public List<Book> read(){
        ObjectInputStream ois = null;
        //1.��ȡ�ļ��е�list
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
     * ��listд�뵽�ļ��е�ͨ�ò���
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
     * ���
     * @param book
     * @return
     */
    @Override
    public List<Book> select(Book book) {
        ObjectInputStream ois = null;
        //1.���ļ��ж�ȡlist
        try {
             ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null){//��booklist��Ϊ��ʱ�����в�ѯ������ȫ��ѯ��������ѯ
                if(book == null ||("".equals(book.getBookName()) && "".equals(book.getIsbn()))){
                    return list;
                }
                List<Book> list1 = new ArrayList<>();//���ڽ��ղ�ѯ������ͼ�����
                //�ж�һ�������Ĳ�ѯ
                if(!"".equals(book.getBookName())){//ʹ��lambda���ʽ���в�ѯ
                    List<Book> collect = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                    return collect;
                }
                if(!"".equals(book.getIsbn())){
                    List<Book> collect = list.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    return collect;
                }
                //�ж����������Ĳ�ѯ
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

        //���listΪ�գ�����һ���ռ���
        return new ArrayList<>();
    }
    /*
    ���
     */
    @Override
    public void add(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        //1.��ȡ�ļ��е�list
        try {
             ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            //2.����list�����һ�������ID����book�����ID��Ȼ��������ͼ��״̬Ϊ���
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
            //3.��listд�����ļ���
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
            oos.writeObject(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("���ʧ��");
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
    ɾ��
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        //1.��ȡ�ļ��е�list
        List<Book> list = read();
        //2.ͨ��ID����Ҫɾ����book����
        Book book = list.stream().filter(b -> b.getId() == id).findFirst().get();
        list.remove(book);
        //3.��listд�����ļ���
        write(list);

    }
    /*
    �޸�
     */
    @Override
    public void update(Book book) {
        //1.��ȡ�ļ���list������
        List<Book> list = read();
        //2.����ID����list�е�book����Ȼ������޸�����
        Book book1 = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();
        book1.setBookName(book.getBookName());
        book1.setIsbn(book.getIsbn());
        book1.setAuthor(book.getAuthor());
        book1.setPublisher(book.getPublisher());
        book1.setType(book.getType());
        //3.��listд�����ļ���
        write(list);

    }
}
