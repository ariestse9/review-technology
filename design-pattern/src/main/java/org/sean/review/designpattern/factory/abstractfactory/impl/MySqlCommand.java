package org.sean.review.designpattern.factory.abstractfactory.impl;

import org.sean.review.designpattern.factory.abstractfactory.Command;

public class MySqlCommand implements Command {
    @Override
    public void execute() {
        System.out.println("MySql command executed.");
    }
}
