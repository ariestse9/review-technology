package org.sean.review.designpattern.factory.abstractfactory.impl;

import org.sean.review.designpattern.factory.abstractfactory.Connection;

public class MySqlConnection implements Connection {
    @Override
    public void commit() {
        System.out.println("MySql commit.");
    }
}
