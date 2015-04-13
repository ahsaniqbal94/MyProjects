package com.faisal.FootBall_App.CRUD;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.faisal.database.Queries;

@Path("/Update")
public class Class_3_UPDATE {
	
	/**
	 * This method will allow you to update data in the Player table or teams table.
	 * In this example we are using both PathParms and the message body (payload).
	 * 
	 * @param brand
	 * @param item_number
	 * @param incomingData
	 * @return
	 * @throws Exception
	 */
	@Path("/Player/{PlayerID}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("PlayerID") int playerid,
									String incomingData) 
								throws Exception {
		
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Queries queriesObject = new Queries(); 
		
		try {
			
			JSONObject playerdata = new JSONObject(incomingData); //we are using json objects to parse data
			
			//call the correct sql method
			http_code=queriesObject.UpdatePlayerInfo(
					playerid, playerdata.optString("PlayerName"),
					playerdata.optString("TeamName"),playerdata.optInt("Age"),playerdata.optString("Nationality"),playerdata.optString("Picture"),playerdata.optString("PictureFS"),playerdata.optString("Position"),playerdata.optInt("MatchedPlayed"),playerdata.optInt("Goals"),playerdata.optInt("PenaltySaved"),playerdata.optInt("Assists"));
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Player has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	public Class_3_UPDATE() {
		
	}

}
