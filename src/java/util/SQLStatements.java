/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Dima
 */
public class SQLStatements {

    // Account
    public static final String CREATE_ACCOUNT = "INSERT INTO `account`( `IMEI`, `level`, `balance`, `enterprise_count`, `created_at`, `last_enter`, `user_name`, `in_flight`, `coordinates_latitude`, `coordinates_longitude`, `investment_in_business` ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM `account` WHERE `idAccount`= ?";
    public static final String GET_ACCOUNT_BY_IMEI = "SELECT * FROM `account` WHERE `IMEI`= ?";
    public static final String EDIT_NICK_NAME = "UPDATE `account` SET `user_name`=? WHERE `IMEI`=?";
    public static final String EDIT_ENTERPRISE_COUNT = "UPDATE `account` SET `enterprise_count` = ? WHERE `IMEI` = ?";
    public static final String EDIT_BALANCE = "UPDATE `account` SET `balance` = ? WHERE `IMEI` = ?";
    //Profile
    public static final String GET_PROFILE_BY_ID = "SELECT * FROM `profile` WHERE `id_profile`= ?";
    public static final String GET_PROFILE_BY_IMEI = "SELECT * FROM `profile` WHERE `IMEI`= ?";
    public static final String CREATE_PROFILE = "INSERT INTO `profile`( `IMEI`, `id_facebook`, `first_name`, `last_name`) VALUES (?,?,?,?)";
    //Enterprise
    public static final String CREATE_ENTERPRISE = "INSERT INTO `enterprise`( `forsquareId`, `locationX`, `locationY`, `name`, `categoryId`, `likes`, `rating`, `createdAt`, `IMEI`, `incasation`, `bought`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_ENTERPRISE_BY_ID = "SELECT * FROM `enterprise` WHERE `id` =?";
    public static final String GET_ENTERPRISE_BY_SQUARE_ID = "SELECT * FROM `enterprise` WHERE `forsquareId` =?";
    public static final String GET_ENTERPRISE_BY_HOLDER = "select * from enterprise where IMEI = ?";
    public static final String UPDATE_INCASATION = "SET `incasation` = ? WHERE `forsquareId` = ?;";
    //improvements
    public static final String GET_IMPROVEMENTS_BALANCE = "SELECT * FROM `improvement_balance` WHERE `sub_level` = ? and `level` = ?;";
    public static final String GET_ALL_IMPROVEMENTS_BALANCE = "SELECT * FROM `improvement_balance`";
    
    public static final String GET_IMPROVEMENTS_ENTERPRISE = "SELECT * FROM `improvement_enterprise` WHERE `sub_level` = ? and `level` = ?;";
    public static final String GET_ALL_IMPROVEMENTS_ENTERPRISE = "SELECT * FROM `improvement_enterprise`";

    public static final String GET_IMPROVEMENTS_INCASATION = "SELECT * FROM `improvement_time_incasation` WHERE `sub_level` = ? and `level` = ?;";
    public static final String GET_ALL_IMPROVEMENTS_INCASATION = "SELECT * FROM `improvement_time_incasation`";

}
