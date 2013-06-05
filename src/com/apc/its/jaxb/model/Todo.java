package com.apc.its.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: kav192
 * Date: 9/12/12
 * Time: 11:54 AM
 */
@XmlRootElement
public class Todo {
    private String id;
    private String summary;
    private String description;

    public Todo() {}

    public Todo(String id, String summary) {
        this.id = id;
        this.summary = summary;
    }

    public Todo(String id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
