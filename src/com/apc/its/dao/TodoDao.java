package com.apc.its.dao;

import com.apc.its.jaxb.model.Todo;

import java.util.HashMap;
import java.util.Map;

/**
 * User: kav192
 * Date: 9/12/12
 * Time: 1:47 PM
 */
public enum TodoDao {
    instance;

    private Map<String, Todo> contentProvider = new HashMap<String, Todo>();

    private TodoDao() {
        Todo todo = new Todo("1", "Learn REST", "Read http://www.vogella.com/articles/REST/article.html");
        contentProvider.put("1", todo);
        todo = new Todo("2", "Do something", "Read complete http://www.vogella.com");
        contentProvider.put("2", todo);
    }

    public Map<String, Todo> getModel() {
        return contentProvider;
    }
}
