// Function to fetch and append data from the database to the table
function displayData() {
    var url = 'ApisFilm'; // URL to the servlet
    ajaxResult(url, 'filmTableBody');
}

// Call the displayData function to fetch and display data when the page loads
window.onload = displayData;

// Function to handle search functionality
$(document).ready(function() {
    $('#searchInput').on('keyup', function() {
        var searchText = $(this).val().toLowerCase();
        $('#filmTableBody tr').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(searchText) > -1)
        });
    });
});

var format;
// Function to handle data format change
function changeDataFormat() {
    format = $('#dataFormatSelect').val();
    var url = 'ApisFilm?format=' + format; // Include format as query parameter
    ajaxResult(url, 'filmTableBody');
}
