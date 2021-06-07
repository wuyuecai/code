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
        //初始化用户数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001, "张大虎", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH,userList);
        //初始化图书数据
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "java实战入门", "张三", Constant.TYPE_COMPUTER, "12-987", "XX出版社", Constant.STATUS_STORAGE));
        bookList.add(new Book(1, "java入门", "李思", Constant.TYPE_COMPUTER, "12-987", "XXyy出版社", Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH, bookList);
    }

    /**
     * 初始化数据，封装通用的初始化方法
     */
    public static void initData(String path,List<?> list) {
        ObjectOutputStream oos = null;
        //创建相关文件夹
        try {
            File directory = new File(path.split("/")[0]+"/");//使用字符串的split的字符串拼接
            File file = new File(path);
            //判断文件夹是否存在
            if (!directory.exists()) {
                directory.mkdir();
            }
            //判断文件是否存在
            if (!file.exists()) {
                file.createNewFile();
                //利用对象输出流将list数据写出到文件中
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//    /**
//     * 初始化用户数据
//     */
//    public static void initUser(List<User> userList) {
//        ObjectOutputStream oos = null;
//        //创建相关文件夹
//        try {
//            File directory = new File("user/");
//            File file = new File(PathConstant.USER_PATH);
//            //判断文件夹是否存在
//            if (!directory.exists()) {
//                directory.mkdir();
//            }
//            //判断文件是否存在
//            if (!file.exists()) {
//                file.createNewFile();
//               List<User> list = new ArrayList<>();
//               //userList中如果没有数据的话，我们自己创建一些
//                if (userList == null) {
//                    list.add(new User(1001, "张大虎", Constant.USER_OK, BigDecimal.TEN));
//                }else {
//                    list = userList;
//                }
//               //利用对象输出流将list数据写出到文件中
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //关闭流
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
