package com.weswaas.api.data.sql;

import com.weswaas.api.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class Table {

    private String name;
    private HashMap<String, SQLTypes> nameType;
    private SQLManager sql;

    public Table(String name, HashMap<String, SQLTypes> nameType, SQLManager sql){
        this.name = name;
        this.nameType = nameType;
        this.sql = sql;
    }

    public String getName(){
        return this.name;
    }

    public HashMap<String, SQLTypes> getValues(){
        return this.nameType;
    }

    private boolean isConnected() {

        Connection connection = sql.getConnection();
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

        Connection connection = sql.getConnection();

        if (!isConnected()) {
            Main.getInstance().getSQL().getConnection();
        }

        return connection;
    }

    public void create(){

        StringBuilder list = new StringBuilder("");
        int i = 1;

        for(String s : this.nameType.keySet()){

            SQLTypes type = nameType.get(s);

            if(i == 1){
                list.append(s + " " + type.getSynthax());
            }else{
                list.append(", " + s + " " + type.getSynthax());
            }

            i++;
        }

        try {
            PreparedStatement sts = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + name + " (" + list.toString().trim() + ")");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(HashMap<String, String> attributes){

        StringBuilder list = new StringBuilder("");
        int i = 1;

        for(String s : attributes.keySet()){

            if(i == 1){
                list.append(s);
            }else{
                list.append(", " + s);
            }

            i++;
        }

        StringBuilder list2 = new StringBuilder("");
        int i2 = 1;

        for(String s2 : attributes.values()){

            if(i2 == 1){
                list2.append("'" + s2 + "'");
            }else{
                list2.append(", '" + s2 + "'");
            }

            i2++;
        }

        try{
            PreparedStatement sts = getConnection().prepareStatement("INSERT INTO " + this.name + " (" + list.toString().trim() + ") VALUES (" + list2.toString().trim() + ")");
            sts.executeUpdate();
            sts.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public boolean isRegistered(String toVerify, String check, String should){
        boolean is = false;

        try{
            PreparedStatement sts = getConnection().prepareStatement("SELECT " + check + " FROM " + this.name + " WHERE " + toVerify + " = '" + should + "'");
            ResultSet rs = sts.executeQuery();
            is = rs.next();
            sts.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return is;
    }

    public String getValue(String what, String condition, String answer){
        String result = null;
        try{
            PreparedStatement sts = getConnection().prepareStatement("SELECT " + what + " FROM " + this.name + " WHERE " + condition + " = '" + answer + "'");
            ResultSet rs = sts.executeQuery();
            while(rs.next()){
                result = rs.getString(what);
            }
            sts.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return result;

    }

    public void update(String what, String newValue, String where, String whereCheck){

        try{
            PreparedStatement sts = getConnection().prepareStatement("UPDATE " + this.name + " SET " + what + " = '" + newValue + "' WHERE " + where + " = '" + whereCheck + "'");
            sts.executeUpdate();
            sts.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public enum SQLTypes{

        INTEGER("INTEGER NOT NULL DEFAULT 0"),
        STRING("VARCHAR (255)");

        private String synthax;

        SQLTypes(String synthax){
            this.synthax = synthax;
        }

        public String getSynthax(){
            return this.synthax;
        }

    }

}
