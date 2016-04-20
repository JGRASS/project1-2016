package model;

import java.sql.Connection;
import java.util.LinkedList;

import connection.DBConnector;
import domen.Sala;

public class Model {
	DBConnector konektor = new DBConnector();
	Connection conn = konektor.connect();
	
	public LinkedList<Sala> prikupiSale(String tipSale){
		
		return null;
	}

}
