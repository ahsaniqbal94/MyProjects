$(document).ready(function(){
	LoadTeams();
	
	$("#UpdatePlayerSelectTeam").change(LoadPlayers);
	$("#UpdatePlayerSelectPlayer").change(LoadPlayerInfo);
	
	
	function LoadPlayerInfo(){
		var Name=$("#UpdatePlayerSelectPlayer").find("option:selected").text();
		
		if(Name=="Not_Selected")
		{	
			window.PlayerID=null;
		//console.log(data);
		$("#UpdatePlayerName").val("");
		$("#UpdatePlayerTeam").val("");
		$("#UpdatePlayerAge").val("");
		$("#UpdatePlayerNationality").val("");
		$("#UpdatePlayerPicture").val("");
		$("#UpdatePlayerPicture").trigger('change');
		$("#UpdatePlayerPictureFS").val("");
		$("#UpdatePlayerPictureFS").trigger('change');
		$("#UpdatePlayerMatches").val("");
		$("#UpdatePlayerGoals").val("");
		$("#UpdatePlayerAssists").val("");
		$("#UpdatePlayerPosition").val("");
		$("#UpdatePlayerPenalty").val("");
		$('#update_response').html("");
		
		return null;
		}
		var ajaxObj = {  
				type: "GET",
				url: "http://localhost:7001/com.faisal.FootBall_App/data/Info/Players/"+Name, 
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.responseText);
				},
				success: function(data) { 
					window.PlayerID=data[0].PlayerID;
					//console.log(data);
					$("#UpdatePlayerName").val(data[0].PlayerName);
					$("#UpdatePlayerTeam").val(data[0].TeamName);
					$("#UpdatePlayerAge").val(data[0].Age);
					$("#UpdatePlayerNationality").val(data[0].Nationality);
					$("#UpdatePlayerPicture").val(data[0].Picture);
					$("#UpdatePlayerPicture").trigger('change');
					$("#UpdatePlayerPictureFS").val(data[0].PictureFS);
					$("#UpdatePlayerPictureFS").trigger('change');
					$("#UpdatePlayerMatches").val(data[0].MatchesPlayed);
					$("#UpdatePlayerGoals").val(data[0].Goals);
					$("#UpdatePlayerAssists").val(data[0].Assists);
					$("#UpdatePlayerPosition").val(data[0].Position);
					$("#UpdatePlayerPenalty").val(data[0].PenaltySaved);
					
					$('#update_response').html("");
					
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json" //request JSON
			};
			
		return $.ajax(ajaxObj);
		
	};
	$('#UpdatePlayerDiscardButton').click(LoadPlayerInfo);
	
	function LoadPlayers(){
		
		var team=$("#UpdatePlayerSelectTeam").find("option:selected").text();
		
		if(team=="Not_Selected")
		{	
			window.PlayerID=null;
		//console.log(data);
		$("#UpdatePlayerName").val("");
		$("#UpdatePlayerTeam").val("");
		$("#UpdatePlayerAge").val("");
		$("#UpdatePlayerNationality").val("");
		$("#UpdatePlayerPicture").val("");
		$("#UpdatePlayerPicture").trigger('change');
		$("#UpdatePlayerPictureFS").val("");
		$("#UpdatePlayerPictureFS").trigger('change');
		$("#UpdatePlayerMatches").val("");
		$("#UpdatePlayerGoals").val("");
		$("#UpdatePlayerAssists").val("");
		$("#UpdatePlayerPosition").val("");
		$("#UpdatePlayerPenalty").val("");
		$('#update_response').html("");
		$("#UpdatePlayerSelectPlayer").html("<option value=\"0\" selected=\"selected\">Not_Selected</option>");
		
		return null;
		}
		
		var ajaxObj = {  
				type: "GET",
				url: "http://localhost:7001/com.faisal.FootBall_App/data/Info/Players/ByTeam/"+team, 
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR.responseText);
				},
				success: function(data) { 
					//console.log(data);
					var html_string = "<option value=\"0\" selected=\"selected\">Not_Selected</option>";
					
					$.each(data, function(key) {
						html_string=html_string+"<option>"+data[key].PlayerName+"</option>" ;
					});
					$("#UpdatePlayerSelectPlayer").html(html_string);
					$("#UpdatePlayerName").val("");
					$("#UpdatePlayerTeam").val("");
					$("#UpdatePlayerAge").val("");
					$("#UpdatePlayerNationality").val("");
					$("#UpdatePlayerPicture").val("");
					$("#UpdatePlayerPicture").trigger('change');
					$("#UpdatePlayerPictureFS").val("");
					$("#UpdatePlayerPictureFS").trigger('change');
					$("#UpdatePlayerMatches").val("");
					$("#UpdatePlayerGoals").val("");
					$("#UpdatePlayerAssists").val("");
					$("#UpdatePlayerPosition").val("");
					$("#UpdatePlayerPenalty").val("");
					$('#update_response').html("");
					$('#update_response').html("");
					
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json" //request JSON
			};
			
		return $.ajax(ajaxObj);

	};
	function LoadTeams(){

			
			var ajaxObj = {  
					type: "GET",
					url: "http://localhost:7001/com.faisal.FootBall_App/data/Info/Teams", 
					contentType:"application/json",
					error: function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR.responseText);
					},
					success: function(data) { 
						//console.log(data);
						var html_string = "<option selected=\"selected\">Not_Selected</option>";
						
						$.each(data, function(key) {
							html_string=html_string+"<option>"+data[key].TeamName+"</option>" ;
						});
						$("#UpdatePlayerSelectTeam").html(html_string);
						
					},
					complete: function(XMLHttpRequest) {
						//console.log( XMLHttpRequest.getAllResponseHeaders() );
					}, 
					dataType: "json" //request JSON
				};
				
			return $.ajax(ajaxObj);
	};
	
	$('#UpdatePlayerForm').submit(function(e){
		
			e.preventDefault(); //cancel form submit
			
			var obj = $('#UpdatePlayerForm').serializeObject();
				
			update(obj,window.PlayerID);
	});

	function update(obj,PlayerId){
		
		var ajaxObj = {  
				type: "PUT",
				url: "http://localhost:7001/com.faisal.FootBall_App/data/Update/Player/" + PlayerId,
				data: JSON.stringify(obj), 
				contentType:"application/json",
				error: function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
					
				},
				success: function(data) {
					//console.log(data);
					if (data[0].HTTP_CODE == 200) {
						$('#update_response').html( data[0].MSG );
					}
					
				},
				complete: function(XMLHttpRequest) {
					//console.log( XMLHttpRequest.getAllResponseHeaders() );
				}, 
				dataType: "json" //request JSON
			};
			
		return $.ajax(ajaxObj);
	};	

});

