package com.db;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(UserDaoFactory2.class);
        UserDao05WithInterface userDao = ctx.getBean("userDao05Interface", UserDao05WithInterface.class);
        userDao.add();
    }
}
