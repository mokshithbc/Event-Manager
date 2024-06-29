// Open and close modal functions
function openModal() {
    const modal = document.getElementById('taskModal');
    
    // Clear input fields
    document.getElementById('timeRange').value = '';
    document.getElementById('title').value = '';
    document.getElementById('description').value = '';
    
    modal.style.display = 'block';
}

function closeModal() {
    const modal = document.getElementById('taskModal');
    modal.style.display = 'none';
}

function closeModalSuccess() {
    const modal = document.getElementById('taskModalSuccess');
    modal.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function () {
    // Fetch tasks for the selected date and display them
	function fetchTasks() {
	    const yearElement = document.getElementById('year');
	    const monthElement = document.getElementById('month');
	    const dayElement = document.getElementById('day');

	    if (yearElement && monthElement && dayElement) {
	        const year = yearElement.value;
	        const month = monthElement.value;
	        const day = dayElement.value;

	        const url = `${contextPath}/TasksServlet?userId=${encodeURIComponent(userId)}&year=${encodeURIComponent(year)}&month=${encodeURIComponent(month)}&day=${encodeURIComponent(day)}`;

	        fetch(url)
	            .then(response => response.json())
	            .then(tasks => {
	                const taskTable = document.getElementById('task-table');
	                const noTasksDiv = document.getElementById('no-tasks');

	                taskTable.innerHTML = ''; // Clear existing table content

	                if (tasks.length === 0) {
	                    noTasksDiv.style.display = 'block';
	                } else {
	                    noTasksDiv.style.display = 'none';

	                    // Create table headers
	                    const headers = ['Time Range', 'Title', 'Description', 'Status', 'Delete', 'Edit'];
	                    const headerRow = document.createElement('tr');
	                    headers.forEach(headerText => {
	                        const headerCell = document.createElement('th');
	                        headerCell.textContent = headerText;
	                        headerRow.appendChild(headerCell);
	                    });
	                    taskTable.appendChild(headerRow);

	                    // Populate table rows with task data
	                    tasks.forEach(task => {
	                        console.log('Fetched task:', task); // Log task to ensure it has an id
	                        const row = document.createElement('tr');

	                        const timeRangeCell = document.createElement('td');
	                        timeRangeCell.textContent = task.timeRange;
	                        row.appendChild(timeRangeCell);

	                        const titleCell = document.createElement('td');
	                        titleCell.textContent = task.title;
	                        row.appendChild(titleCell);

	                        const descriptionCell = document.createElement('td');
	                        descriptionCell.textContent = task.description;
	                        row.appendChild(descriptionCell);

	                        // Status cell with checkbox
	                        const statusCell = document.createElement('td');
	                        const statusCheckbox = document.createElement('input');
	                        statusCheckbox.type = 'checkbox';
	                        statusCheckbox.checked = task.status === 'true';
	                        statusCheckbox.onclick = () => updateTaskStatus(task.id, statusCheckbox.checked);
	                        statusCell.appendChild(statusCheckbox);
	                        row.appendChild(statusCell);

	                        // Delete button cell
	                        const deleteCell = document.createElement('td');
	                        const deleteButton = document.createElement('button');
	                        deleteButton.textContent = 'Delete';
	                        deleteButton.onclick = () => {
	                            console.log('Delete button clicked for task with id:', task.id); // Log task.id here
	                            deleteTask(task.id);
	                        };
	                        deleteCell.appendChild(deleteButton);
	                        row.appendChild(deleteCell);

	                        // Edit button cell
	                        const editCell = document.createElement('td');
	                        const editButton = document.createElement('button');
	                        editButton.textContent = 'Edit';
	                        editButton.onclick = () => editTask(task);
	                        editCell.appendChild(editButton);
	                        row.appendChild(editCell);

	                        taskTable.appendChild(row);
	                    });
	                }
	            })
	            .catch(error => console.error('Error fetching tasks:', error));
	    } else {
	        console.error("One or more elements not found");
	    }
	}
    // Submit task function
    function submitTask(event) {
        event.preventDefault();  // Prevent form from submitting normally

        const timeRange = document.getElementById('timeRange').value;
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;

        if (!timeRange || !title || !description) {
            alert('Please fill in all fields.');
            return;
        }

        const year = document.getElementById('year').value;
        const month = document.getElementById('month').value;
        const day = document.getElementById('day').value;
        const date = `${year}-${month}-${day}`;

        const url = `${contextPath}/AddTaskServlet?userId=${encodeURIComponent(userId)}&date=${encodeURIComponent(date)}&timeRange=${encodeURIComponent(timeRange)}&title=${encodeURIComponent(title)}&description=${encodeURIComponent(description)}`;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
        .then(response => response.text())
        .then(data => {
            if (data === "true") {
                closeModal();
                fetchTasks(); // Update displayed tasks
                const successModal = document.getElementById('taskModalSuccess');
                successModal.style.display = 'block';
                document.getElementById('successMessage').textContent = 'Task added successfully!';
            } else {
                alert('Failed to add task. Please try again.');
            }
        })
        .catch(error => console.error('Error adding task:', error));
    }

    // Function to update task status
    function updateTaskStatus(taskId, status) {
        const url = `${contextPath}/UpdateTaskStatusServlet?taskId=${encodeURIComponent(taskId)}&status=${encodeURIComponent(status)}`;
        
        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
        .then(response => response.text())
        .then(data => {
            if (data === "true") {
                console.log('Task status updated successfully');
            } else {
                console.error('Failed to update task status');
            }
        })
        .catch(error => console.error('Error updating task status:', error));
    }

    // Function to delete a task
	function deleteTask(taskId) {
	    console.log('deleteTask called with taskId:', taskId); // Log taskId here
	    const url = `${contextPath}/DeleteTaskServlet?taskId=${encodeURIComponent(taskId)}`;

	    fetch(url, {
	        method: 'DELETE',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded',
	        }
	    })
	    .then(response => response.text())
	    .then(data => {
	        if (data === "true") {
	            fetchTasks(); // Update displayed tasks after deletion
	            console.log('Task deleted successfully');
	        } else {
	            console.error('Failed to delete task');
	        }
	    })
	    .catch(error => console.error('Error deleting task:', error));
	}

    // Function to edit a task (you need to implement this)
    function editTask(task) {
        // Populate the modal with task details and open it for editing
        document.getElementById('timeRange').value = task.timeRange;
        document.getElementById('title').value = task.title;
        document.getElementById('description').value = task.description;

        openModal();
        document.getElementById('submitTaskBtn').textContent = 'Update';
        
        // Add submit event listener to update the task
        document.getElementById('taskForm').onsubmit = function (event) {
            event.preventDefault();
            updateTask(task.id);
        };
    }

    // Function to update a task (you need to implement this)
    function updateTask(taskId) {
        const timeRange = document.getElementById('timeRange').value;
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;

        const url = `${contextPath}/UpdateTaskServlet?taskId=${encodeURIComponent(taskId)}&timeRange=${encodeURIComponent(timeRange)}&title=${encodeURIComponent(title)}&description=${encodeURIComponent(description)}`;

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
        .then(response => response.text())
        .then(data => {
            if (data === "true") {
                closeModal();
                fetchTasks(); // Update displayed tasks after updating
                console.log('Task updated successfully');
            } else {
                console.error('Failed to update task');
            }
        })
        .catch(error => console.error('Error updating task:', error));
    }

    // Populate year dropdown
    const yearDropdown = document.getElementById('year');
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    for (let i = currentYear - 100; i <= currentYear + 100; i++) {
        let option = document.createElement('option');
        option.value = i;
        option.text = i;
        yearDropdown.add(option);
    }

    // Populate month dropdown
    const monthDropdown = document.getElementById('month');
    const months = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    months.forEach((month, index) => {
        let option = document.createElement('option');
        option.value = index + 1;
        option.text = month;
        monthDropdown.add(option);
    });

    // Populate day dropdown based on selected month and year
    function populateDays() {
        const dayDropdown = document.getElementById('day');
        dayDropdown.innerHTML = ''; // Clear existing options

        const selectedYear = yearDropdown.value;
        const selectedMonth = monthDropdown.value;
        const daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();

        for (let i = 1; i <= daysInMonth; i++) {
            let option = document.createElement('option');
            option.value = i;
            option.text = i;
            dayDropdown.add(option);
        }
    }

    yearDropdown.addEventListener('change', populateDays);
    monthDropdown.addEventListener('change', populateDays);

    // Set default values to current date
    yearDropdown.value = currentYear;
    monthDropdown.value = currentDate.getMonth() + 1;
    populateDays();
    document.getElementById('day').value = currentDate.getDate();

    yearDropdown.addEventListener('change', fetchTasks);
    monthDropdown.addEventListener('change', fetchTasks);
    document.getElementById('day').addEventListener('change', fetchTasks);

    // Attach submitTask to the form submit event
    const taskForm = document.getElementById('taskForm');
    if (taskForm) {
        taskForm.addEventListener('submit', submitTask);
    }

    // Fetch tasks for the current date on page load
    fetchTasks();

    // Attach openModal function to buttons or elements that should open the modal
    const openModalButton = document.getElementById('openModalButton');
    if (openModalButton) {
        openModalButton.addEventListener('click', openModal);
    }

    // Attach closeModal function to buttons or elements that should close the modal
    const closeModalButton = document.getElementById('closeModalButton');
    if (closeModalButton) {
        closeModalButton.addEventListener('click', closeModal);
    }

    // Attach closeModalSuccess function to buttons or elements that should close the success modal
    const closeModalSuccessButton = document.getElementById('closeModalSuccessButton');
    if (closeModalSuccessButton) {
        closeModalSuccessButton.addEventListener('click', closeModalSuccess);
    }
});
