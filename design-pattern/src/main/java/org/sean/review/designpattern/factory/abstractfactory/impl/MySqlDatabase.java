package org.sean.review.designpattern.factory.abstractfactory.impl;

import org.sean.review.designpattern.factory.abstractfactory.Command;
import org.sean.review.designpattern.factory.abstractfactory.Connection;
import org.sean.review.designpattern.factory.abstractfactory.Database;

public class MySqlDatabase implements Database {
    @Override
    public Connection getConnection() {
        return new MySqlConnection();
    }

    @Override
    public Command getCommand() {
        return new MySqlCommand();
    }
}
