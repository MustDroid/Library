package com.emoke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseWordRepository implements IWordRepository {
    private Connection connection;

    public DatabaseWordRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getWordCategoryFromWord(String word) {
        int idWordCategory = 1;

        try {
            PreparedStatement st = connection.prepareStatement("SELECT idWordCategory FROM wordsnew WHERE word=?");
            st.setString(1, word);
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                idWordCategory = rs.getInt("idWordCategory");
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return idWordCategory;
    }
}
