/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0661137
 */
@Named
@ApplicationScoped 
public class wordController {
    private word gameWord = new word();
    
      private void getPostsFromDB() {
        try (Connection conn = DBUtils.getConnection()) {       
            Statement stmt = conn.createStatement();
            Random rnd = new Random();
            int rand = rnd.nextInt(29)+1;
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts WHERE wordId = " + rand);
            while (rs.next()) {
                gameWord = new word(
                        rs.getString("title"),
                        rs.getInt("wordId")
                );
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(wordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public word getGameWord() {
        return gameWord;
    }

    public void setGameWord(word gameWord) {
        this.gameWord = gameWord;
    }
      
}
