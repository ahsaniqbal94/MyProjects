package com.faisal.FootBall_App.CRUD;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.faisal.database.Queries;

@Path("/Insert")
public class Class_2_POST {

	String returnString = null;
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	Queries queriesObject = new Queries();

	@Path("/Players/")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response PosttoDB(String incomingData) throws Exception {
		try {
			JSONObject data = new JSONObject(incomingData);

			int http_code = queriesObject.InsertPlayerRecord(
					data.optInt("PlayerID"), data.optString("PlayerName"),
					data.optString("TeamName"), data.optString("CountryName"));

			if (http_code == 200) {

				jsonObject.put("HTTP_CODE", "200");
				// jsonObject.put("MSG", "Data Entered Successfully");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Request was not Processed")
						.build();

			}

		} catch (Exception e) {
			return Response.status(500).entity("Request was not Processed")
					.build();

		}

		return Response.ok(returnString).build();
	}

	@Path("/Teams/")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response PostTeamtoDB(String incomingData) throws Exception {
		try {
			JSONObject data = new JSONObject(incomingData);

			int http_code = queriesObject.InsertTeamRecord(
					data.optString("TeamName"), data.optString("TeamCode"));

			if (http_code == 200) {

				jsonObject.put("HTTP_CODE", "200");
				// jsonObject.put("MSG", "Data Entered Successfully");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Request was not Procesed")
						.build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Request was not Procesed")
					.build();

		}

		return Response.ok(returnString).build();
	}

}
