package uz.micros.jstore;

import uz.micros.jstore.entity.blog.Post;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    public static List<Post> getPosts(){
        List<Post> posts = new ArrayList<>();

        Connection conn = getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("select * from posts");


            while(res.next()){
                Post p = new Post();

                p.setId(res.getInt(1));
                p.setSubject(res.getString(2));
                p.setText(res.getString(3));
                p.setDate(res.getDate(4));
                p.setAuthor(res.getString(5));

                posts.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    private static Connection getConnection() {

        Connection res = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            res = DriverManager.getConnection("jdbc:mysql://localhost:3306/jstore",
                    "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
}
