package model;

import java.sql.Connection;
import java.util.LinkedList;

import connection.DBConnector;
import domen.Sala;

public class Model {
	DBConnector konektor = new DBConnector();
	Connection conn = konektor.connect();
	String upit = "";
	
	public LinkedList<Sala> prikupiSale(String tipSale){
		if(tipSale == "RacunskiCentar"){
		}
		return null;
	}

}
