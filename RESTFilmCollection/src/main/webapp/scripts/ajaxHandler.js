// Function to make the AJAX request
function ajaxResult(address, resultRegion) {
	var request = getRequestObject();
	request.onreadystatechange = function() {
		showResponseText(request, resultRegion);
	};
	request.open("GET", address, true);
	request.send(null);
}

// Function to create XMLHttpRequest object
function getRequestObject() {
	if (window.XMLHttpRequest) {
		// For modern browsers
		return new XMLHttpRequest();
	} else {
		// For old IE browsers
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

$(document).ready(function() {
	$('#addFilmForm').submit(function(event) {
		// Prevent default form submission
		event.preventDefault();

		// Serialize form data
		var formData = $(this).serialize();

		// Make AJAX POST request to servlet
		$.ajax({
			type: 'POST',
			url: 'ApisFilm',
			data: formData,
			success: function(response) {
				console.log('Film added successfully');
				// Close the modal
				$('#addFilmModal').modal('hide');
				// Reload the table to display the updated data
				displayData();
			},
			error: function(xhr, status, error) {

				console.error('Error adding film:', error);

				// Display error message to the user (if needed)
				alert('Error adding film. Please try again.');
			}
		});
	});
});
//Function to delete a film
function deleteFilm(id) {
	// Confirm deletion
	if (confirm("Are you sure you want to delete this film?")) {
		// Make AJAX request to DeleteFilms servlet
		$.ajax({
			type: 'DELETE',
			url: 'ApisFilm?id=' + id,
			success: function(response) {
				console.log('Film deleted successfully');
				// Reload the page after deletion
				window.location.reload();
			},
			error: function(xhr, status, error) {
				console.error('Error deleting film:', error);
				// Handle error (e.g., display an error message)
			}
		});
	}
}

// Function to submit edited film details
function submitEditFilm() {
	var formData = $('#editFilmForm').serialize();
	var format = $('#dataFormatSelect').val();
	console.log('formData:' + formData)

	$.ajax({
		url: 'ApisFilm',
		type: 'PUT',
		contentType: getContentType(format),
		data: getFormattedData(formData, format),
		success: function(response) {
			// Handle success
			$('#editFilmModal').modal('hide');
			// Reload the table after successful editing
			displayData(); // Call the function to refresh the table
		},
		error: function(xhr, status, error) {
			// Handle error
		}
	});
}

function getContentType(format) {
	switch (format) {
		case 'json':
			return 'application/json';
		case 'xml':
			return 'application/xml';
		case 'text':
			return 'text/plain';
		default:
			console.error('Unsupported format: ' + format);
			return '';
	}
}

function getFormattedData(formData, format) {
	switch (format) {
		case 'json':
			return JSON.stringify(parseFormDataToJson(formData));
		case 'xml':
			return parseFormDataToXml(formData);
		case 'text':
			return parseFormDataToText(formData);
		default:
			throw new Error('Unsupported format: ' + format);
	}
}

function parseFormDataToJson(formData) {
	let jsonData = {};
	formData.split('&').forEach(function(pair) {
		var keyValue = pair.split('=');
		jsonData[keyValue[0]] = decodeURIComponent(keyValue[1]);
	});
	return jsonData;
}

function parseFormDataToXml(formData) {
	let xmlString = '<film>';
	formData.split('&').forEach(function(pair) {
		var keyValue = pair.split('=');
		xmlString += '<' + keyValue[0] + '>' + decodeURIComponent(keyValue[1]) + '</' + keyValue[0] + '>';
	});
	xmlString += '</film>';
	return xmlString;
}
function parseFormDataToText(formData) {
	// Decode form data and extract values
	var decodedData = decodeURIComponent(formData);
	var pairs = decodedData.split('&');
	var textData = pairs.map(function(pair) {
		return pair.split('=')[1];
	}).join(':');
	return textData;
}


// Function to open the edit modal with film details
function openEditModal(filmId) {
	var url = 'ApisFilm?id=' + filmId + '&format=' + format;

	// Perform AJAX request to fetch film details
	$.ajax({
		url: url, // URL to your server-side script to fetch film details
		type: 'GET', // Use GET method
		success: function(response) {
			// Populate form fields with fetched film details based on format
			switch (format) {
				case 'xml':
					// Parse XML response and populate form fields
					var $xml = $(response);
					$('#editFilmId').val($xml.find('id').text());
					$('#editTitle').val($xml.find('title').text());
					$('#editYear').val($xml.find('year').text());
					$('#editDirector').val($xml.find('director').text());
					$('#editStars').val($xml.find('stars').text());
					$('#editReview').val($xml.find('review').text());
					break;
				case 'json':
					// Parse JSON response and populate form fields
					$('#editFilmId').val(response.id);
					$('#editTitle').val(response.title);
					$('#editYear').val(response.year);
					$('#editDirector').val(response.director);
					$('#editStars').val(response.stars);
					$('#editReview').val(response.review);
					break;
				case 'text':
					// Populate form fields with text response
					// Assuming text response contains film details separated by new lines
					var lines = response.split('\n');
					console.log('line:', lines)
					$('#editFilmId').val(lines[0].trim());
					$('#editTitle').val(lines[1].trim());
					$('#editYear').val(lines[2].trim());
					$('#editDirector').val(lines[3].trim());
					$('#editStars').val(lines[4].trim());
					$('#editReview').val(lines[5].trim());
					break;
				default:
					console.error('Unsupported format: ' + format);
					return;
			}

			// Open the modal
			$('#editFilmModal').modal('show');
		},
		error: function(xhr, status, error) {
			// Handle errors
			console.error(xhr.responseText);
		}
	});
}

//Function to retrieve film details by ID using AJAX
function getFilmById(filmId) {
	var url = 'ApisFilm?id=' + filmId + '&format=' + format;
	console.log(url);
	$.ajax({
		url: url,
		method: 'GET',
		success: function(response) {
			// Call displayFilmDetails function with retrieved film data
			displayFilmDetails(response);
		},
		error: function(xhr, status, error) {
			// Handle error
			console.error('Error fetching film details:', error);
		}
	});
}

//Function to display film details in a popup
function displayFilmDetails(film) {
	var popupContent = '';
	console.log(popupContent)
	if (format === 'json') {

		popupContent = '<div><strong>ID:</strong> ' + film.id + '</div>' +
			'<div><strong>Title:</strong> ' + film.title + '</div>' +
			'<div><strong>Year:</strong> ' + film.year + '</div>' +
			'<div><strong>Director:</strong> ' + film.director + '</div>' +
			'<div><strong>Stars:</strong> ' + film.stars + '</div>' +
			'<div><strong>Review:</strong> ' + film.review + '</div>';
	} else if (format === 'xml') {
		// Handle XML format
		var $xml = $(film); // Assuming 'film' is already in XML format
		console.log($xml)
		var id = $xml.find('id').text();
		var title = $xml.find('title').text();
		var year = $xml.find('year').text();
		var director = $xml.find('director').text();
		var stars = $xml.find('stars').text();
		var review = $xml.find('review').text();

		popupContent = '<div><strong>ID:</strong> ' + id + '</div>' +
			'<div><strong>Title:</strong> ' + title + '</div>' +
			'<div><strong>Year:</strong> ' + year + '</div>' +
			'<div><strong>Director:</strong> ' + director + '</div>' +
			'<div><strong>Stars:</strong> ' + stars + '</div>' +
			'<div><strong>Review:</strong> ' + review + '</div>';
	} else {
		// Default to plain text format
		var filmEntries = film.split("\r\n\r\n");

		// Iterate through each film entry
		filmEntries.forEach(function(entry) {
			// Split each entry into lines
			var lines = entry.split("\n");
			var filmData = {};
			console.log(filmData)
			// Extract data for each film
			lines.forEach(function(line, index) {
				var value = line.trim(); // Trim whitespace including '\r' if present
				filmData[index] = value;
				console.log('value', value)
				console.log('filmdata', filmData)
			});

			// Create HTML content for each film entry
			popupContent += '<div><strong>ID:</strong> ' + filmData[0] + '</div>' +
				'<div><strong>Title:</strong> ' + filmData[1] + '</div>' +
				'<div><strong>Year:</strong> ' + filmData[2] + '</div>' +
				'<div><strong>Director:</strong> ' + filmData[3] + '</div>' +
				'<div><strong>Stars:</strong> ' + filmData[4] + '</div>' +
				'<div><strong>Review:</strong> ' + filmData[5] + '</div>' +
				'<hr>'; // Add a horizontal line between film entries
		});

	}


	// Create a Bootstrap modal for the popup
	var popupModal = $('<div class="modal fade" tabindex="-1" role="dialog"></div>');
	var modalDialog = $('<div class="modal-dialog" role="document"></div>');
	var modalContent = $('<div class="modal-content"></div>');
	var modalHeader = $('<div class="modal-header">' +
		'<h5 class="modal-title">Film Details</h5>' +
		'<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
		'<span aria-hidden="true">&times;</span></button></div>');
	var modalBody = $('<div class="modal-body">' + popupContent + '</div>');
	var modalFooter = $('<div class="modal-footer">' +
		'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
		'</div>');

	// Append elements to construct the modal
	modalContent.append(modalHeader, modalBody, modalFooter);
	modalDialog.append(modalContent);
	popupModal.append(modalDialog);

	// Append the modal to the body and show it
	$('body').append(popupModal);
	popupModal.modal('show');

	// Remove the modal from the DOM when it's closed
	popupModal.on('hidden.bs.modal', function() {
		$(this).remove();
	});
}
