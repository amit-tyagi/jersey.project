package com.apc.its.dao;

import com.sun.deploy.net.proxy.DummyAutoProxyHandler;

import javax.ejb.EJB;

/**
 * User: kav192
 * Date: 9/13/12
 * Time: 8:54 AM
 */
public class CommentDAO extends DAO {
    @EJB
    private DAO dao;
}
