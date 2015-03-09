$(document).ready(function() {

	$('#btnCancel').click(function(e) {
		$('#playerID').val('');
		$('#playerName').val('');
		$('#teamName').val('');
		$('#countryName').val('');
		$('#teamCode').val('');
		$('#btnSavePlayer').attr('disabled', true);
		$('#btnSaveTeam').attr('disabled', true);
	});

	$("#playerID").keypress(function(e) {
		// if the letter is not digit then display error and don't type anything
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			// display error message
			$("#lblStatus").html("Digits Only...").show().fadeOut("slow");
			return false;
		}
	});

	$(".alpha-only").on("keydown", function(event) {
		// Ignore controls such as backspace
		var arr = [ 8, 16, 17, 35, 36, 37, 38, 39, 40, 45, 46 ];
		// Allow letters
		for (var i = 65; i <= 90; i++) {
			arr.push(i);
		}
		if (jQuery.inArray(event.which, arr) === -1) {
			event.preventDefault();
		}
	});

	$(".alpha-only").on("input", function() {
		var regexp = /[^a-zA-Z]/g;
		if ($(this).val().match(regexp)) {
			$(this).val($(this).val().replace(regexp, ''));
		}
	});

	var $input = $('input:text'), $save = $('#btnSavePlayer').add('#btnSaveTeam');

	$save.attr('disabled', true);
	$input.keyup(function() {
		var trigger = false;
		$input.each(function() {
			if (!$(this).val()) {
				trigger = true;
			}
		});
		trigger ? $save.attr('disabled', true) : $save.removeAttr('disabled');
	});

});
