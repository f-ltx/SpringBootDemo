package cn.ltx.springboot.controller.test;

import cn.ltx.springboot.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestMysql {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/springmvcdemo";
    private static final String DBUSER = "root";
    private static final String PASSWORD = "mysqladmin";

    private static Log log = LogFactory.getLog(TestMysql.class);

    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
            log.info("数据库连接成功。");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库连接失败。");
        }
        return conn;
    }

    public static void close() {
        if (null != conn) {
            try {
                conn.close();
                log.info("数据库关闭。");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int doCrete(User user) throws Exception{
        String sql = "insert into t_user(username,password,sex)values(?,?,?)" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getSex());
        return pstmt.executeUpdate();
    }

    public static int[] doCreteBatch(List<User> users) throws Exception{
        String sql = "insert into t_user(username,password,sex)values(?,?,?)" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (User user : users) {
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getSex());
            pstmt.addBatch();
        }
        return pstmt.executeBatch();
    }

    public static List<User> findAll() throws Exception{
        String sql = "select * from t_user" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        List<User> users = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setSex(rs.getString(4));
            users.add(user);
        }
        return users;
    }

    public static User findById(long id) throws Exception{
        String sql = "select * from t_user where id=?" ;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1,id);
        User user = new User();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setSex(rs.getString(4));
        }
        return user;
    }

    public static List<User> findByList(List<Long> list) throws Exception{
        StringBuffer sql = new StringBuffer("select * from t_user where id in(") ;
        for (Long aLong : list) {
            sql.append(aLong).append(",");
        }
        sql.delete(sql.lastIndexOf(","),sql.length()).append(")");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        List<User> users = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setSex(rs.getString(4));
            users.add(user);
        }
        return users;
    }

    public static void main(String[] args) throws Exception {
        //connection
        getConnection();
        //insert
//        saveBatch();
//        saveOne();

        //find
        listAll();
        listOne();
        listByList();

        //close
        close();
    }

    public static void listAll() throws Exception{
        long start = System.currentTimeMillis();
        findAll();
        long end = System.currentTimeMillis();
        log.info("listAll" + (end-start));
    }

    public static void listOne() throws Exception{
        long start = System.currentTimeMillis();
        for(int i=1;i<=1000;i++){
            findById(i);
        }
        long end = System.currentTimeMillis();
        log.info("listOne" + (end-start));
    }

    public static void listByList() throws Exception{
        long start = System.currentTimeMillis();
        List<Long> list = new ArrayList<>();
        for(int i=1;i<=1000;i++){
            list.add(Long.parseLong(String.valueOf(i)));
        }
        findByList(list);
        long end = System.currentTimeMillis();
        log.info("listByList" + (end-start));
    }

    public static void saveBatch() throws Exception{
        long start = System.currentTimeMillis();
        List<User> users = new ArrayList<>();
        for(int i=0;i<5000;i++){
            User user = new User();
            user.setUsername("测试" + i);
            user.setPassword("admin" + i);
            if(i/2==0){
                user.setSex("女");
            }else {
                user.setSex("男");
            }
            users.add(user);
        }
        doCreteBatch(users);
        long end = System.currentTimeMillis();
        log.info("saveBatch" + (end-start));
    }

    public static void saveOne() throws Exception{
        long start = System.currentTimeMillis();
        for(int i=0;i<5000;i++){
            User user = new User();
            user.setUsername("test" + i);
            user.setPassword("admin" + i);
            if(i/2==0){
                user.setSex("女");
            }else {
                user.setSex("男");
            }
            doCrete(user);
        }
        long end = System.currentTimeMillis();
        log.info("saveOne" + (end-start));
    }
}