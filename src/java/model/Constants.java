/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 *
 * @author Dima
 */
public class Constants {
    
    public class ServletConstant{
        public static final String IMEI = "IMEI";
        public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
        public static final String CHARSET_ENCODING="UTF-8";
    }
    
    public class ParameterRequestConstant{
        //   Account
        public static final String USER_NAME = "userName";
        public static final String ENTERPRISE_ID = "enterpriseId";
        
        
        
        // Global
        public static final String GLOBAL = "SOME ERROR, Call to admin :)";
    }
    
    public enum ReqestLink{
        ACCOUNT_EDIT_USERNAME,
        ACCOUNT_INCASATION;
    }
    
    
}
