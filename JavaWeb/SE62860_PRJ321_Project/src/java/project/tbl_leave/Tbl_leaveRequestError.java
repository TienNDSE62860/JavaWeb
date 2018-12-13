/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_leave;

import java.io.Serializable;

/**
 *
 * @author tien29214
 */
public class Tbl_leaveRequestError implements Serializable{
    private String dateFromErr;
    private String dateToErr;

    /**
     * @return the dateFromErr
     */
    public String getDateFromErr() {
        return dateFromErr;
    }

    /**
     * @param dateFromErr the dateFromErr to set
     */
    public void setDateFromErr(String dateFromErr) {
        this.dateFromErr = dateFromErr;
    }

    /**
     * @return the dateToErr
     */
    public String getDateToErr() {
        return dateToErr;
    }

    /**
     * @param dateToErr the dateToErr to set
     */
    public void setDateToErr(String dateToErr) {
        this.dateToErr = dateToErr;
    }

    
    
}
