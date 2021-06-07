package com.bjpowernode.util;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InitDataUtil {
    public static void main(String[] args) {
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001, "�Ŵ�", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH,userList);
        //��ʼ��ͼ������
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "javaʵս����", "����", Constant.TYPE_COMPUTER, "12-987", "XX������", Constant.STATUS_STORAGE));
        bookList.add(new Book(1, "java����", "��˼", Constant.TYPE_COMPUTER, "12-987", "XXyy������", Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH, bookList);
    }

    /**
     * ��ʼ�����ݣ���װͨ�õĳ�ʼ������
     */
    public static void initData(String path,List<?> list) {
        ObjectOutputStream oos = null;
        //��������ļ���
        try {
            File directory = new File(path.split("/")[0]+"/");//ʹ���ַ�����split���ַ���ƴ��
            File file = new File(path);
            //�ж��ļ����Ƿ����
            if (!directory.exists()) {
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()) {
                file.createNewFile();
                //���ö����������list����д�����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//    /**
//     * ��ʼ���û�����
//     */
//    public static void initUser(List<User> userList) {
//        ObjectOutputStream oos = null;
//        //��������ļ���
//        try {
//            File directory = new File("user/");
//            File file = new File(PathConstant.USER_PATH);
//            //�ж��ļ����Ƿ����
//            if (!directory.exists()) {
//                directory.mkdir();
//            }
//            //�ж��ļ��Ƿ����
//            if (!file.exists()) {
//                file.createNewFile();
//               List<User> list = new ArrayList<>();
//               //userList�����û�����ݵĻ��������Լ�����һЩ
//                if (userList == null) {
//                    list.add(new User(1001, "�Ŵ�", Constant.USER_OK, BigDecimal.TEN));
//                }else {
//                    list = userList;
//                }
//               //���ö����������list����д�����ļ���
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //�ر���
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
