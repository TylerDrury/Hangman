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
import java.util.List;
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
    private word thisWord = new word();
    private List<word> wordList = new ArrayList<>();
    
     public wordController() {
        thisWord = new word("", 0);
        getPostsFromDB();
    }
      private void getPostsFromDB() {
        try (Connection conn = DBUtils.getConnection()) {       
            Statement stmt = conn.createStatement();
            Random rnd = new Random();
            int rand = rnd.nextInt(29)+1;
            ResultSet rs = stmt.executeQuery("SELECT * FROM words");
            while (rs.next()) {
               word w = new word();
               w.setWord(rs.getString("title"));
               w.setWordId(rs.getInt("wordId"));
               wordList.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(wordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public word getThisWord() {
        return thisWord;
    }

    public void setThisWord(word thisWord) {
        this.thisWord = thisWord;
    }

    public List<word> getWordList() {
        return wordList;
    }

   public word getByWordId(int id) {
        for (word i : wordList) {
            if (i.getWordId() == id) {
                return i;
            }
        }
        return null;
    }

    
      
}
