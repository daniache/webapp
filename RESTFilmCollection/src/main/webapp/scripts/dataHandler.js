// Function to handle the AJAX response and append data to the table body
function showResponseText(request, resultRegion) {
	if (request.readyState == 4 && request.status == 200) {

		// Check the content type of the response
		var contentType = request.getResponseHeader("Content-Type");

		if (contentType.indexOf("application/json") !== -1) {

			// Handle JSON response
			var jsonData = JSON.parse(request.responseText);

			// Clear existing table data 
			$.each(jsonData, function(index, film) {
				var row = $('<tr>');
				row.append($('<td>').text(film.id));
				row.append($('<td>').text(film.title));
				row.append($('<td>').text(film.year));
				row.append($('<td>').text(film.director));
				row.append($('<td>').text(film.stars));
				row.append($('<td>').text(film.review));

				// Add edit and delete buttons
				var actions = $('<td>');
				var viewButton = $('<button>').text('View').addClass('btn btn-info btn-sm mr-1').html('<i class="material-icons">visibility</i>').click(function() {
					// Call function to display film details in a popup
					getFilmById(film.id);
				});
				var editButton = $('<button>').text('Edit').addClass('btn btn-primary btn-sm mr-1').html('<i class="material-icons">edit</i>').click(function() {
					// Now, editFilm should be accessible here
					openEditModal(film.id);

				});
				var deleteButton = $('<button>').text('Delete').addClass('btn btn-danger btn-sm').html('<i class="material-icons">delete</i>').click(function() {
					// Call delete function
					deleteFilm(film.id);
				});
				actions.append(viewButton);
				actions.append(editButton);
				actions.append(deleteButton);
				row.append(actions);


				$('#filmTableBody').append(row);
			});

		} else if (contentType.indexOf("application/xml") !== -1) {

			// Handle XML response
			var xmlData = $(request.responseText);

			xmlData.find('film').each(
				function() {
					var film = $(this);
					var row = $('<tr>');
					row.append($('<td>').text(
						film.find('id').text()));
					row.append($('<td>').text(
						film.find('title').text()));
					row.append($('<td>').text(
						film.find('year').text()));
					row.append($('<td>').text(
						film.find('director').text()));
					row.append($('<td>').text(
						film.find('stars').text()));
					row.append($('<td>').text(
						film.find('review').text()));
					// Add edit,view and delete buttons
					var actions = $('<td>');
					var viewButton = $('<button>').text('View').addClass('btn btn-info btn-sm mr-1').html('<i class="material-icons">visibility</i>').click(function() {
						// Call function to display film details in a popup
						getFilmById(film.find('id'.text));
					});
					var editButton = $('<button>').text('Edit').addClass('btn btn-primary btn-sm mr-1').html('<i class="material-icons">edit</i>').click(function() {
						openEditModal(film.find('id').text());

					});
					var deleteButton = $('<button>').text('Delete').addClass('btn btn-danger btn-sm').html('<i class="material-icons">delete</i>').click(function() {
						deleteFilm(film.find('id').text());
					});
					actions.append(viewButton);
					actions.append(editButton);
					actions.append(deleteButton);
					row.append(actions);

					$('#filmTableBody').append(row);

				});

		} else if (contentType.indexOf("text/plain") !== -1) {

			// Split the response into individual film entries
			var filmEntries = request.responseText.split("\r\n\r\n");

			// Iterate through each film entry
			filmEntries.forEach(function(entry) {
				// Split each entry into lines
				var lines = entry.split("\n");
				var filmData = {};
				//console.log("lines:", lines);
				// Extract data for each film
				lines.forEach(function(line) {
					var parts = line.split(": ");
					var key = parts[0].trim();
					var value = parts[1];
					filmData[key] = value;
				});

				// Create a new row for the film entry
				var row = $("<tr>");
				row.append($('<td>').text(filmData["ID"]));
				row.append($('<td>').text(filmData["Title"]));
				row.append($('<td>').text(filmData["Year"]));
				row.append($('<td>').text(filmData["Director"]));
				row.append($('<td>').text(filmData["Stars"]));
				row.append($('<td>').text(filmData["Review"]));

				// Add edit, view and delete buttons
				var actions = $('<td>');
				var viewButton = $('<button>').text('View').addClass('btn btn-info btn-sm mr-1').html('<i class="material-icons">visibility</i>').click(function() {
					// Call function to display film details in a popup
					getFilmById(filmData["ID"]);
				});
				var editButton = $('<button>').text('Edit').addClass('btn btn-primary btn-sm mr-1').html('<i class="material-icons">edit</i>').click(function() {
					openEditModal(filmData["ID"]);
				});
				var deleteButton = $('<button>').text('Delete').addClass('btn btn-danger btn-sm').html('<i class="material-icons">delete</i>').click(function() {
					// Call delete function
					deleteFilm(filmData["ID"]);
				});
				actions.append(viewButton);
				actions.append(editButton);
				actions.append(deleteButton);
				row.append(actions);


				// Append the row to the table body
				$('#filmTableBody').append(row);
			});

		}

	}
}


