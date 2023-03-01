package com.kgisl.baydetails.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Baydetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int floorno;
    private String wing;
    private String bayno;
    private String associatecode;
    private String associatename;
    public Baydetails() {
    }
    private String baytype;   
     private String cstatus;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    public String getWing() {
        return wing;
    }
    public void setWing(String wing) {
        this.wing = wing;
    }
    public int getFloorno() {
        return floorno;
    }
    public void setFloorno(int floorno) {
        this.floorno = floorno;
    }
    public String getBayno() {
        return bayno;
    }
    public void setBayno(String bayno) {
        this.bayno = bayno;
    }
    public String getAssociatecode() {
        return associatecode;
    }
    public void setAssociatecode(String associatecode) {
        this.associatecode = associatecode;
    }
    public String getAssociatename() {
        return associatename;
    }
    public void setAssociatename(String associatename) {
        this.associatename = associatename;
    }
    public String getBaytype() {
        return baytype;
    }
    public void setBaytype(String baytype) {
        this.baytype = baytype;
    }
    public String getCstatus() {
        return cstatus;
    }
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
    
    
}
