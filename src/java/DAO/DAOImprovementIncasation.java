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
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.MODELImprovementBalance;
import model.MODELImprovementEnterprise;
import model.MODELImprovementIncasation;
import util.SQLStatements;

/**
 *
 * @author Dima
 */
@Stateless
public class DAOImprovementIncasation {

    public MODELImprovementIncasation getImprovmentEnterprise(int subLevel, int level) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_IMPROVEMENTS_INCASATION, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setInt(1, subLevel);
            mPreparedStatement.setInt(2, level);
            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
                return parseFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException |  NamingException ex) {
            System.out.println("OGO: 44 " + ex.getMessage());
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

    public ArrayList<MODELImprovementIncasation> getImprovmentEnterpriseAll() {
        ArrayList<MODELImprovementIncasation> returnList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ALL_IMPROVEMENTS_INCASATION, Statement.RETURN_GENERATED_KEYS);
            rs = mPreparedStatement.executeQuery();
            while (rs.next()) {
                returnList.add(parseFromResultSet(rs));
            }
            return returnList;
        } catch (SQLException | NamingException ex) {
            System.out.println("OGO: 44 " + ex.getMessage());
            return returnList;
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

    private MODELImprovementIncasation parseFromResultSet(ResultSet rs) throws SQLException {
        MODELImprovementIncasation model = new MODELImprovementIncasation(
                rs.getInt("id"),
                rs.getInt("level"),
                rs.getInt("sub_level"),
                rs.getInt("limil"),
                rs.getInt("price")
        );

        return model;
    }
}
