package com.faisal.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataBase {

	private static DataSource database = null;
	private static Context context = null;

	public static DataSource DBConn() throws Exception {

		if (database != null)
			return (database);

		try {
			if (context == null)
				context = new InitialContext();

			database = (DataSource) context.lookup("Faisal_FootBall_APP");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return database;
	}

	protected Connection DBConnect() throws Exception {
		Connection conn = null;

		try {
			conn = DBConn().getConnection();
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
