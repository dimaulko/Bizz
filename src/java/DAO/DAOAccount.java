/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.Gson;
import ejb.EJBAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.AccountDB;
import model.LatLong;
import util.SQLStatements;
import util.UtilTime;

/**
 *
 * @author Dima
 */
@Stateless
public class DAOAccount {

    @EJB
    private DAOImprovementBalance daoImprovementBalance;

    public AccountDB create(AccountDB accountDB) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            long time = UtilTime.getTimeStamp();
            mPreparedStatement = conn.prepareStatement(SQLStatements.CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, accountDB.getIMEI());
            mPreparedStatement.setInt(2, 0);
            mPreparedStatement.setLong(3, accountDB.getBalance());
            mPreparedStatement.setInt(4, 0);
            mPreparedStatement.setString(5, String.valueOf(time));
            mPreparedStatement.setString(6, String.valueOf(time));
            mPreparedStatement.setString(7, "User_" + String.valueOf(time));
            mPreparedStatement.setInt(8, 0);
            mPreparedStatement.setDouble(9, 0.);
            mPreparedStatement.setDouble(10, 0.);
            mPreparedStatement.setInt(11, 0);
            mPreparedStatement.executeUpdate();

            rs = mPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                System.out.println("last_inserted_id : " + String.valueOf(last_inserted_id));
                return getAccountByID(last_inserted_id);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    private AccountDB getAccountByID(int id) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ACCOUNT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setInt(1, id);

            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
                return parseFromResultSet(rs);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    public AccountDB getAccountByIMEI(String IMEI) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.GET_ACCOUNT_BY_IMEI, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, IMEI);

            rs = mPreparedStatement.executeQuery();
            if (rs.next()) {
                return parseFromResultSet(rs);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    public String editUserName(String IMEI, String userName) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.EDIT_NICK_NAME, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setString(1, userName);
            mPreparedStatement.setString(2, IMEI);
            int editedRows = mPreparedStatement.executeUpdate();
            if (editedRows > 0) {
                return new Gson().toJson(getAccountByIMEI(IMEI));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    public String editEnterpriseCount(String IMEI, int count) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.EDIT_ENTERPRISE_COUNT, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setInt(1, count);
            mPreparedStatement.setString(2, IMEI);
            int editedRows = mPreparedStatement.executeUpdate();
            if (editedRows > 0) {
                return new Gson().toJson(getAccountByIMEI(IMEI));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    public String editBalance(String IMEI, long count) {
        Connection conn = null;
        PreparedStatement mPreparedStatement = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jndi/bizz");
            conn = ds.getConnection();
            mPreparedStatement = conn.prepareStatement(SQLStatements.EDIT_BALANCE, Statement.RETURN_GENERATED_KEYS);
            mPreparedStatement.setLong(1, count);
            mPreparedStatement.setString(2, IMEI);
            int editedRows = mPreparedStatement.executeUpdate();
            if (editedRows > 0) {
                return new Gson().toJson(getAccountByIMEI(IMEI));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("OGO: " + ex.getMessage());
            return null;
        } catch (NamingException ex) {
            Logger.getLogger(EJBAccount.class.getName()).log(Level.SEVERE, null, ex);
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

    private AccountDB parseFromResultSet(ResultSet mResultSet) throws SQLException {
        AccountDB accountDB;
        accountDB = new AccountDB();
        accountDB.setIMEI(mResultSet.getString("IMEI"));
        accountDB.setLevel(mResultSet.getInt("level"));
        accountDB.setBalance(mResultSet.getInt("balance"));
        accountDB.setEnterpriseCount(mResultSet.getInt("enterprise_count"));
        accountDB.setCreatedAt(mResultSet.getLong("created_at"));
        accountDB.setLastEnter(mResultSet.getLong("last_enter"));
        accountDB.setUserName(mResultSet.getString("user_name"));
        accountDB.setInFlight((mResultSet.getInt("in_flight") != 0));
        accountDB.setLatLong(new LatLong(mResultSet.getDouble("coordinates_latitude"), mResultSet.getDouble("coordinates_longitude")));
        accountDB.setInvestmentInBusiness(mResultSet.getInt("investment_in_business"));
        accountDB.setImprovementBalance(mResultSet.getInt("improvement_balance"));
        accountDB.setImprovementEnterpriseCount(mResultSet.getInt("improvement_enterprise_count"));
        accountDB.setProfitOnLevel(mResultSet.getInt("profit_on_level"));
        accountDB.setImprovementTimeIncasation(mResultSet.getInt("improvement_time_incasation"));
        return accountDB;

    }
}
