/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_department;

import java.io.Serializable;

/**
 *
 * @author tien29214
 */
public class Tbl_departmentDTO implements Serializable{
    private String depID;
    private String name;

    public Tbl_departmentDTO(){}
    public Tbl_departmentDTO(String depID, String name) {
        this.depID = depID;
        this.name = name;
    }

    /**
     * @return the depID
     */
    public String getDepID() {
        return depID;
    }

    /**
     * @param depID the depID to set
     */
    public void setDepID(String depID) {
        this.depID = depID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
