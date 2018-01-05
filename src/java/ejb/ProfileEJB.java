/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import model.ProfileDB;
import util.SQLStatements;

/**
 *
 * @author Dima
 */
@Stateless
public class ProfileEJB {

    public ProfileDB create( ProfileDB profile, String IMEI){
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.CREATE_PROFILE, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, IMEI);
            mPreparedStatement.setString(2, profile.getFacebookLink());
            mPreparedStatement.setString(3, profile.getUserFirstName());
            mPreparedStatement.setString(4, profile.getUserLastName());
            mPreparedStatement.executeUpdate();
            rs = mPreparedStatement.getGeneratedKeys();
                if(rs.next())
                {
                    int last_inserted_id = rs.getInt(1);
                    System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                }
            return null;
        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("ppz");
            }
        }
        return null;
    }
}
