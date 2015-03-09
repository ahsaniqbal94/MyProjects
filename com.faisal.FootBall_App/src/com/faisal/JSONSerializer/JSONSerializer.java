package com.faisal.JSONSerializer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.ESAPI;

/**
 * It will convert data processed from the DataBase into JSON array format
 * 
 * @param rs
 * @return JSON Array
 * @throws Exception
 */

public class JSONSerializer {

	public JSONArray JsonArray(ResultSet rs) throws Exception {

		JSONArray JsonArray = new JSONArray();
		String temp = null;

		try {

			java.sql.ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				int numColumn = rsmd.getColumnCount();

				JSONObject jsonObject = new JSONObject();

				for (int i = 1; i < numColumn + 1; i++) {

					String column_name = rsmd.getColumnName(i);

					if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
						jsonObject.put(column_name, rs.getArray(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {

						temp = rs.getString(column_name); // saving column
						temp = ESAPI.encoder().canonicalize(temp);
						temp = ESAPI.encoder().encodeForHTML(temp);
						jsonObject.put(column_name, temp);

						// jsonObject.put(column_name, rs.getString(i));

					} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
						jsonObject.put(column_name, rs.getNString(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
						jsonObject.put(column_name, rs.getInt(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
						jsonObject.put(column_name, rs.getTimestamp(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
						jsonObject.put(column_name, rs.getBigDecimal(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
						jsonObject.put(column_name, rs.getInt(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
						jsonObject.put(column_name, rs.getBlob(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
						jsonObject.put(column_name, rs.getBoolean(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
						jsonObject.put(column_name, rs.getDate(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
						jsonObject.put(column_name, rs.getFloat(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
						jsonObject.put(column_name, rs.getDouble(i));
					} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
						jsonObject.put(column_name, rs.getInt(i));
					} else {
						jsonObject.put(column_name, rs.getObject(i));
					}

				}// for end

				JsonArray.put(jsonObject);
			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return JsonArray;
	}
}
