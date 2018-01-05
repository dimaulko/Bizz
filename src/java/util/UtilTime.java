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
public class UtilTime {
    public static final int SECONDS_IN_DAY = 86_400;
    
    
    public static long getTimeStamp(){
        return System.currentTimeMillis()/1000;
    }
    public static long getSecondsFromHours(int hours){
        return hours*60*60;
    }
}
