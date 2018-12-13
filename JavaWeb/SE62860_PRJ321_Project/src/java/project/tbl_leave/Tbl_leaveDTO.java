/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_leave;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author tien29214
 */
public class Tbl_leaveDTO implements Serializable{
    private int leaveID;
    private Date fromDate;
    private Date fromTo;
    private boolean accept;
    private String empID;
    private String requestReason;
    private String rejectReason;
    
    public Tbl_leaveDTO(){}

    public Tbl_leaveDTO(int leaveID, Date fromDate, Date fromTo, boolean accept, String empID, String requestReason, String rejectReason) {
        this.leaveID = leaveID;
        this.fromDate = fromDate;
        this.fromTo = fromTo;
        this.accept = accept;
        this.empID = empID;
        this.requestReason = requestReason;
        this.rejectReason = rejectReason;
    }

    /**
     * @return the leaveID
     */
    public int getLeaveID() {
        return leaveID;
    }

    /**
     * @param leaveID the leaveID to set
     */
    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the fromTo
     */
    public Date getFromTo() {
        return fromTo;
    }

    /**
     * @param fromTo the fromTo to set
     */
    public void setFromTo(Date fromTo) {
        this.fromTo = fromTo;
    }

    /**
     * @return the accept
     */
    public boolean isAccept() {
        return accept;
    }

    /**
     * @param accept the accept to set
     */
    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    /**
     * @return the empID
     */
    public String getEmpID() {
        return empID;
    }

    /**
     * @param empID the empID to set
     */
    public void setEmpID(String empID) {
        this.empID = empID;
    }

    /**
     * @return the requestReason
     */
    public String getRequestReason() {
        return requestReason;
    }

    /**
     * @param requestReason the requestReason to set
     */
    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    /**
     * @return the rejectReason
     */
    public String getRejectReason() {
        return rejectReason;
    }

    /**
     * @param rejectReason the rejectReason to set
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    
}
