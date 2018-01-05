/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.AccountDB;
import model.ProfileDB;
import util.SQLStatements;

/**
 *
 * @author Dima
 */
@Stateless
public class DAOProfile {

    public ProfileDB create(ProfileDB profileDB) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.CREATE_PROFILE, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, profileDB.getIMEI());
            mPreparedStatement.setString(2, profileDB.getFacebookLink());
            mPreparedStatement.setString(3, profileDB.getUserFirstName());
            mPreparedStatement.setString(4, profileDB.getUserLastName());
            mPreparedStatement.executeUpdate();

            rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return getProfileByID(last_inserted_id);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO: 52 " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (mPreparedStatement != null) {
                    mPreparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("ppz");
            }
        }
    }

    private ProfileDB getProfileByID(int id) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_PROFILE_BY_ID, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setInt(1, id);

            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
                return parseFromResultSet(rs);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("OGO 92: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (mPreparedStatement != null) {
                    mPreparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("ppz");
            }
        }
    }

    public ProfileDB getProfileByIMEI(String IMEI) {
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_PROFILE_BY_IMEI, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, IMEI);

            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
                return parseFromResultSet(rs);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("OGO 133: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (mPreparedStatement != null) {
                    mPreparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("ppz");
            }
        }
    }

    private ProfileDB parseFromResultSet(ResultSet mResultSet) throws SQLException {
        ProfileDB profileDB = new ProfileDB(
                mResultSet.getString("IMEI"),
                mResultSet.getString("id_facebook"),
                mResultSet.getString("first_name"),
                mResultSet.getString("last_name"));
        return profileDB;

    }
}
