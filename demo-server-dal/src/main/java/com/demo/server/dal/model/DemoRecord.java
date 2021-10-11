package com.demo.server.dal.model;

import java.util.Date;

public class DemoRecord {
    private Long id;

    private String demoName;

    private String demoAddress;

    private Date created;

    private Date updated;

    private Long creator;

    private Long modifier;

    private String deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName == null ? null : demoName.trim();
    }

    public String getDemoAddress() {
        return demoAddress;
    }

    public void setDemoAddress(String demoAddress) {
        this.demoAddress = demoAddress == null ? null : demoAddress.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}