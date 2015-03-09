package com.faisal.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.faisal.JSONSerializer.JSONSerializer;

public class Queries extends DataBase {
	PreparedStatement query = null;
	Connection conn = null;

	ResultSet rs = null;

	JSONSerializer JSONSerializerObject = new JSONSerializer();
	JSONArray GetjsonArray = new JSONArray();

	public JSONArray returnPlayerInfo(String playerName) throws Exception {

		try {
			conn = DBConnect();
			query = conn
					.prepareStatement("Select PlayerName , TeamName , CountryName from Players where PlayerName = ?");
			query.setString(1, playerName);
			rs = query.executeQuery();

			GetjsonArray = JSONSerializerObject.JsonArray(rs);
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return GetjsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return GetjsonArray;
		} finally {
			if (conn != null)
				conn.close();
		}
		return GetjsonArray;
	}

	public JSONArray returnTeamInfo(String teamName) throws Exception {

		try {
			conn = DBConnect();
			query = conn
					.prepareStatement("select ManagerName,TeamName,LeagueName from TeamManager where TeamName = ?");

			query.setString(1, teamName);
			rs = query.executeQuery();

			GetjsonArray = JSONSerializerObject.JsonArray(rs);
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return GetjsonArray;
		}

		catch (Exception e) {
			e.printStackTrace();
			return GetjsonArray;
		} finally {
			if (conn != null)
				conn.close();
		}

		return GetjsonArray;

	}

	public JSONArray returnManagerInfo(String managerName) throws Exception {
		try {
			conn = DBConnect();
			query = conn
					.prepareStatement("Select ManagerName,ManagerInfo,TeamName,AppointedDate from TeamManager where ManagerName = ?");

			query.setString(1, managerName);
			rs = query.executeQuery();

			GetjsonArray = JSONSerializerObject.JsonArray(rs);
			query.close();

		} catch (SQLException e) {
			e.printStackTrace();
			return GetjsonArray;

		} catch (JSONException e) {
			e.printStackTrace();
			return GetjsonArray;
		} finally {
			if (!(conn.equals(null)))
				conn.close();
		}
		return GetjsonArray;
	}

	public int InsertPlayerRecord(int playerID, String playerName,
			String teamName, String countryName) throws Exception {
		try {
			conn = DBConnect();
			query = conn.prepareStatement("insert into Players"
					+ "(PlayerID,PlayerName,TeamName,CountryName)"
					+ "values(?,?,?,?)");

			query.setInt(1, playerID);
			query.setString(2, playerName);
			query.setString(3, teamName);
			query.setString(4, countryName);
			query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!conn.equals(null)) {
				conn.close();
			}
		}

		return 200;
	}

	public int InsertTeamRecord(String teamName, String teamCode)
			throws Exception {
		try {
			conn = DBConnect();
			query = conn.prepareStatement("insert into Teams"
					+ "(TeamName,TeamCode)" + "values(?,?)");

			query.setString(1, teamName);
			query.setString(2, teamCode);
			query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!conn.equals(null)) {
				conn.close();
			}
		}

		return 200;
	}

	// public int postIntoDB_1(String Age) throws Exception {
	//
	// try {
	// conn = DBConnect();
	// query = conn.prepareStatement("insert into WS" + "(age)"
	// + "values(?)");
	//
	// query.setString(1, Age);
	// query.executeUpdate();
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	//
	// finally {
	// if (!(conn.equals(null)))
	// conn.close();
	// }
	//
	// return 200;
	// }
	//
	// public int postIntoDB(String Name, String FatherName, String Age,
	// String DateOfBirth, String Occupation, String MartialStatus,
	// String Brand, String UserStatus) throws Exception {
	//
	// try {
	// conn = DBConnect();
	// query = conn
	// .prepareStatement("insert into WS"
	// +
	// "(Name,FatherName,Age,DateOfBirth,Occupation,MartialStatus,Brand,UserStatus)"
	// + "values(?, ? ,?, ?, ?, ?, ?, ?)");
	//
	// query.setString(1, Name);
	// query.setString(2, FatherName);
	// query.setString(3, Age);
	// query.setString(4, DateOfBirth);
	// query.setString(5, Occupation);
	// query.setString(6, MartialStatus);
	// query.setString(7, Brand);
	// query.setString(8, UserStatus);
	//
	// query.executeUpdate();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return 500;
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// if (!(conn.equals(null)))
	// conn.close();
	// }
	//
	// return 200;
	// }
	//
	// public int UpdateItem(int Id, String Age) throws Exception {
	//
	// try {
	// conn = DBConnect();
	// query = conn.prepareStatement("Update WS" + " set Age = ?"
	// + "Where IDNumber= ?");
	//
	// query.setString(1, Age);
	// query.setInt(2, Id);
	// query.executeUpdate();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return 500;
	//
	// } finally {
	// if (!(conn.equals(null)))
	// conn.close();
	// }
	//
	// return 200;
	// }
	//
	// public int DeleteItem(int id) throws Exception {
	//
	// try {
	//
	// conn = DBConnect();
	// query = conn.prepareStatement("delete from WS"
	// + "where IDNumber = ? ");
	// query = conn.prepareStatement("select into bck"
	// + "from WS where IDNumber= ?");
	//
	// query.setInt(1, id);
	// query.setInt(2, id);
	// query.executeUpdate();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return 500;
	// } finally {
	// if (conn != null)
	// conn.close();
	// }
	//
	// return 200;
	// }
	//
}
