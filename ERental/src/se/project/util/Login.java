package se.project.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import se.project.database.Context;

public class Login {

  public static boolean validate(String user, String pass) {
    try {
      Connection con = Context.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "SELECT * FROM useraccount WHERE user =? AND pass =?");
      ps.setString(1, user);
      ps.setString(2, pass);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        con.close();
        return true;

      } else {
        con.close();
        return false;
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    return true;

  }

  public static void signUp(String user, String pass) {
    try {

      Connection con = Context.getConnection();
      PreparedStatement pss = con.prepareStatement(
          "INSERT INTO useraccount SET user = ? , pass = ?");
      pss.setString(1, user);
      pss.setString(2, pass);
      pss.execute();
      con.close();
    } catch (Exception ee) {
      JOptionPane.showMessageDialog(null, "Wrong password or username");
    } finally {
      JOptionPane.showMessageDialog(null, "Success . You have created an account.");
    }


  }
}
