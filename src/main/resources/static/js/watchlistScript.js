document.addEventListener("DOMContentLoaded", () => {
    const movieTable = document.getElementById("movieTable").getElementsByTagName('tbody')[0];
    const movieCount = document.getElementById("movieCount");

    document.getElementById("addMovie").addEventListener("click", () => {
        // Get input values
        let title = document.getElementById("title").value.trim();
        let rating = document.getElementById("rating").value.trim();
        let priority = document.getElementById("priority").value.trim();
        let comment = document.getElementById("comment").value.trim();

        // Ensure inputs are not empty
        if (title === "" || rating === "" || priority === "" || comment === "") {
            alert("Please fill out all fields!");
            return;
        }

        // Create a new row
        let newRow = movieTable.insertRow();

        // Insert cells
        newRow.insertCell(0).textContent = title;
        newRow.insertCell(1).textContent = rating;
        newRow.insertCell(2).textContent = priority;
        newRow.insertCell(3).textContent = comment;

        // Create "Update" button
        let updateCell = newRow.insertCell(4);
        let updateButton = document.createElement("button");
        updateButton.textContent = "Update";
        updateButton.classList.add("update-btn");
        updateCell.appendChild(updateButton);

        // Update movie count
        updateMovieCount();

        // Clear input fields
        document.getElementById("title").value = "";
        document.getElementById("rating").value = "";
        document.getElementById("priority").value = "";
        document.getElementById("comment").value = "";
    });

    function updateMovieCount() {
        let rowCount = movieTable.rows.length;
        movieCount.textContent = `Number of movies to watch: ${rowCount}`;
    }

    updateMovieCount(); // Initial update
});
