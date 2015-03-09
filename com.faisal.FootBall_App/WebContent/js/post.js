$(document).ready(function() {
					var $playerForm = $('#enterPlayerData');
					var $teamForm = $('#enterTeamData');

					$('#btnSavePlayer').click(function(e) {

										e.preventDefault();
										var jsObj = $playerForm.serializeObject(), ajaxObj
										{
										}
										;
										ajaxObj = {
											type : "POST",
											url : "http://192.168.0.100:7001/com.faisal.FootBall_App/data/Insert/Players/",
											data : JSON.stringify(jsObj),
											contentType : "application/json",
											error : function(jqXHR, textStatus,
													errorThrown) {
												alert("Data was not entered..");
												console.log("Error" + jqXHR.getAllResponseHeaders()+ "" + errorThrown);
											},
											success : function(data) {
												if (data[0].HTTP_CODE == 200) {
													alert("Data Entered Successfully");
												}
												
											},
											complete : function(XMLHttpRequest) {
												// console.log(XMLHttpResquest.getAllResponseHeaders());
											},
											dataType : "json"
										};
										$.ajax(ajaxObj);
									});
					
					
					
					$('#btnSaveTeam').click(function(e) {

						e.preventDefault();
						var jsObj = $teamForm.serializeObject(), ajaxObj
						{
						}
						;
						ajaxObj = {
							type : "POST",
							url : "http://192.168.0.100:7001/com.faisal.FootBall_App/data/Insert/Teams/",
							data : JSON.stringify(jsObj),
							contentType : "application/json",
							error : function(jqXHR, textStatus,
									errorThrown) {
								alert("Data was not entered..");
								console.log("Error" + jqXHR.getAllResponseHeaders()+ "" + errorThrown);
							},
							success : function(data) {
								if (data[0].HTTP_CODE == 200) {
									alert("Data Entered Successfully");
								}
								
							},
							complete : function(XMLHttpRequest) {
								// console.log(XMLHttpResquest.getAllResponseHeaders());
							},
							dataType : "json"
						};
						$.ajax(ajaxObj);
					});
				});