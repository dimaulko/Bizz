/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dbmodels.Enterprise;
import dbmodels.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.enterprise.EnterpriseIncome;
import util.SQLStatements;
import util.UtilTime;

/**
 *
 * @author Dima
 */
@Stateless
public class DAOEnterprise {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Enterprise getByFoursqaureId(String forsquareId) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ENTERPRISE_BY_SQUARE_ID);
            mPreparedStatement.setString(1, forsquareId);

            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
//                int last_inserted_id = rs.getInt(1);
//                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return parseFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(DAOEnterprise.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Enterprise> getEnterpriseList(String holderId) {
        ArrayList<Enterprise> returnList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet mResultSet = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ENTERPRISE_BY_HOLDER);
            mPreparedStatement.setString(1, holderId);

            mResultSet = mPreparedStatement.executeQuery();
            while (mResultSet.next()) {
                returnList.add(parseFromResultSet(mResultSet));
            }
            return returnList;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(DAOEnterprise.class.getName()).log(Level.SEVERE, null, ex);
            return returnList;
        } finally {
            try {
                if (mResultSet != null) {
                    mResultSet.close();
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

    public Enterprise getById(int id) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ENTERPRISE_BY_ID);
            mPreparedStatement.setInt(1, id);
            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
//                int last_inserted_id = rs.getInt(1);
//                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return parseFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(DAOEnterprise.class.getName()).log(Level.SEVERE, null, ex);
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

    public Enterprise create(EnterpriseIncome enterpriseIncome) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.CREATE_PROFILE, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, enterpriseIncome.getForsqareId());
            mPreparedStatement.setDouble(2, enterpriseIncome.getLocation().getLat());
            mPreparedStatement.setDouble(3, enterpriseIncome.getLocation().getLng());
            mPreparedStatement.setString(4, enterpriseIncome.getName());
            mPreparedStatement.setInt(5, enterpriseIncome.getCategories() != null ? 1 : 0);
            mPreparedStatement.setInt(6, enterpriseIncome.getLikes().getCount());
            mPreparedStatement.setDouble(7, enterpriseIncome.getRating());
            mPreparedStatement.setDouble(8, enterpriseIncome.getCreatedAt());
            mPreparedStatement.setInt(9, enterpriseIncome.getAccountId());
            mPreparedStatement.setDouble(10, enterpriseIncome.getIncasationAt());
            mPreparedStatement.executeUpdate();

            rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return getById(last_inserted_id);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO180: " + ex.getMessage());
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

    public Enterprise create(Enterprise enterprise) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            System.out.println("EN: " + enterprise.toString());
            long time = UtilTime.getTimeStamp();
            mPreparedStatement = conn.prepareStatement(SQLStatements.CREATE_ENTERPRISE, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, enterprise.getId());
            mPreparedStatement.setDouble(2, enterprise.getLocation().getLat());
            mPreparedStatement.setDouble(3, enterprise.getLocation().getLng());
            mPreparedStatement.setString(4, enterprise.getName());
            mPreparedStatement.setInt(5, 0);
            mPreparedStatement.setDouble(6, 0);
            mPreparedStatement.setDouble(7, 0);
            mPreparedStatement.setLong(8, time);
            mPreparedStatement.setString(9, enterprise.getHolder());
            mPreparedStatement.setLong(10, time);
            mPreparedStatement.setLong(11, enterprise.getBought());
            mPreparedStatement.executeUpdate();

            rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return getById(last_inserted_id);
            } else {
                return null;
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("OGO:232 " + ex.getMessage());
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

    public void incasationEnterprise(String enterpriseId, long incasationTime) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            System.out.println("EN: " + String.valueOf(enterpriseId) + " : " + String.valueOf(incasationTime));
            long time = System.currentTimeMillis();
            mPreparedStatement = conn.prepareStatement(SQLStatements.UPDATE_INCASATION, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setLong(1, incasationTime);
            mPreparedStatement.setString(2, enterpriseId);
            mPreparedStatement.executeUpdate();
            rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last_update_id : " + String.valueOf(last_inserted_id));
//                return getById(last_inserted_id);
            } else {
//                return null;
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("OGO:232 " + ex.getMessage());
//            return null;
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

    private Enterprise parseFromResultSet(ResultSet mResultSet) throws SQLException {
        Enterprise enterpriseIncome = new Enterprise();
        enterpriseIncome.setId(mResultSet.getString("forsquareId"));
//                mResultSet.getString("forsquareId"),
//                new LatLong(mResultSet.getDouble("locationX"), mResultSet.getDouble("locationY")),
        enterpriseIncome.setName(mResultSet.getString("name"));
        enterpriseIncome.setLocation(new Location(mResultSet.getDouble("locationX"), mResultSet.getDouble("locationY")));
        enterpriseIncome.setHolder(mResultSet.getString("IMEI"));
        enterpriseIncome.setBought(mResultSet.getInt("bought"));
        enterpriseIncome.setCreatedAt(mResultSet.getLong("createdAt"));
        enterpriseIncome.setIncasation(mResultSet.getLong("incasation"));
//                new enterpriseCategory(),
//                mResultSet.getDouble("rating"),
//                mResultSet.getLong("createdAt"),
//                mResultSet.getInt("accountId"),
//                mResultSet.getLong("incasation"),
//                // todo: new Object
//                mResultSet.getInt("bought")
//        );
        return enterpriseIncome;
    }

}
