package org.project;

import Application.MenuTerminal;
import BDconnection.BDconnection;
import BDconnection.Database;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        BDconnection.iniciar();

        Connection conn = Database.getInstance().getConnection();

        MenuTerminal menu = new MenuTerminal(conn);
        menu.iniciar();

        Database.getInstance().closeConnection();
    }
}