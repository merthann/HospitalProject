package com.example.hospital_management.entities;

import com.example.hospital_management.entities.base.BaseEntity;

public class Doctor{
    private long id;
    private String title;
    private String fname;
    private String lname;
    private long policlinicId;
    private long assistantId;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getPoliclinicId() {
        return policlinicId;
    }

    public void setPoliclinicId(long policlinicId) {
        this.policlinicId = policlinicId;
    }

    public long getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(long assistantId) {
        this.assistantId = assistantId;
    }
}
