package com.faisal.FootBall_App.CRUD;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.faisal.database.Queries;

@Path("/Info")
public class Class_1_GET {

	Queries queriesObject = new Queries();
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObject,data = new JSONObject();
	static String Message = "UNMATCHED.... Enter correct field or Server was Unreachable....";
	String getData,returnString = null;

	// /////////////////////////////////PLAYERS///////////////////////////////
	@Path("/Players/{playerName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SinglePlayerInfo(@PathParam("playerName") String playerName)
			throws Exception {
		try {
			jsonArray = queriesObject.returnPlayerInfo(playerName);
			getData = jsonArray.toString();

		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).entity(Message).build();
		}

		return Response.ok(getData).build();
	}

	// /////////////////////////////////PLAYERS///////////////////////////////

	// ///////////////////////////////////TEAMS///////////////////////////////
	@Path("/Teams/{teamName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response TeamInfo(@PathParam("teamName") String teamName)
			throws Exception {

		try {
			jsonArray = queriesObject.returnTeamInfo(teamName);
			getData = jsonArray.toString();

		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).entity(Message).build();
		}

		return Response.ok(getData).build();
	}

	// ///////////////////////////////////TEAMS///////////////////////////////

	// //////////////////////////////Manager//////////////////////////////////

	@Path("/Manager/{managerName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ManagerInfo(@PathParam("managerName") String managerName)
			throws Exception {
		try {
			jsonArray = queriesObject.returnManagerInfo(managerName);
			getData = jsonArray.toString();

		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).entity(Message).build();
		}
		return Response.ok(getData).build();
	}
	
	/////////////////////////////Manager////////////////////////////////
	@Path("/Insert/{record}")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response PosttoDB(String incomngData) throws Exception {
		try {
			data = new JSONObject(incomngData);
			int http_code = queriesObject.InsertPlayerRecord(data.optInt("ID"),
					data.optString("Name"), data.optString("Team"),
					data.optString("Country"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Data Entered Successfully");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Request was not Procesed")
						.build();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).entity("Request was not Procesed")
					.build();

		}

		return Response.ok(returnString).build();
	}
}
