package org.sean.review.designpattern.factory.abstractfactory;

public interface Database {

    Connection getConnection();

    Command getCommand();

}
