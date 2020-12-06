package com.weswaas.api.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class SQLManager {

    private String url_base, host, user, pass, database;
    private Connection connection;

    private ArrayList<Table> tables = new ArrayList<>();

    public SQLManager(String url_base, String host, String database, String user, String pass) {
        this.url_base = url_base;
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.database = database;
        connect();
        tables();
    }

    public void connect(){
        try{
            this.connection = DriverManager.getConnection(url_base + host + "/" + database, user, pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private boolean isConnected() {
        try {
            if ((connection == null) || connection.isClosed() || (!connection.isValid(5))) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getConnection() {
        if (!isConnected()) {
            connect();
        }

        return this.connection;
    }

    public Table getTable(String name){
        for(Table table : this.tables){
            if(table.getName().equalsIgnoreCase(name)){
                return table;
            }
        }
        return null;
    }

    public void tables(){

        HashMap<String, Table.SQLTypes> map = new HashMap<>();
        map.put("uuid", Table.SQLTypes.STRING);
        map.put("name", Table.SQLTypes.STRING);
        map.put("ip", Table.SQLTypes.STRING);
        map.put("color", Table.SQLTypes.STRING);
        Table table = new Table("api", map, this);
        table.create();
        this.tables.add(table);

        HashMap<String, Table.SQLTypes> mapUHC = new HashMap<>();
        map.put("uuid", Table.SQLTypes.STRING);
        map.put("wins", Table.SQLTypes.INTEGER);
        Table tableUHC = new Table("uhc", mapUHC, this);
        this.tables.add(tableUHC);

    }

    public void insert(Table table, HashMap<String, String> attributes){

        table.insert(attributes);

    }

    public boolean isRegistered(Table table, String toVerify, String valueWichIsInTable, String shouldBe){

        return table.isRegistered(toVerify, valueWichIsInTable, shouldBe);

    }

    public String getValue(Table table, String what, String condition, String answer){

        return table.getValue(what, condition, answer);

    }

	/*
	 * What = quoi? quelle colonne? coins? kills?
	 * newValue = la nouvelle valeur. Ex: '15', 'TySzrR', ...
	 * where = ou? Quelle ligne? quel joueur? Ex: 'uuid', 'name'. C'est pour savoir en général quel joueur cibler
	 * whereCheck = la réponse? Si à 'where' on a mit 'uuid', on indiquera ici l'uuid du joueur. Ex: p.getUniqueId().toString();
	 */

    public void update(Table table, String what, String newValue, String where, String whereCheck){

        table.update(what, newValue, where, whereCheck);

    }

}
