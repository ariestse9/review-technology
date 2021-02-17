package org.sean.review.designpattern.factory.abstractfactory;

import org.sean.review.designpattern.factory.abstractfactory.impl.MySqlDatabase;

public class DatabaseClient {

    public static void main(String[] args) {
        Database database = new MySqlDatabase();
        Connection connection = database.getConnection();
        Command command = database.getCommand();

        command.execute();
        connection.commit();
    }

}
