<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>
    <link rel="stylesheet" href="view/css/WelcomeStyles.css">
</head>
<body>
<h1>Login Successful!!</h1>
<div class="container">
    <select id="year" class="dropdown"></select>
    <select id="month" class="dropdown"></select>
    <select id="day" class="dropdown"></select>
</div>
<button class="plus-button" onclick="openModal();">+</button>

<div id="task-container" class="container">
    <table id="task-table" class="task-table">
        <thead>
            <tr>
                <th>Time Range</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
        </thead>
        <tbody id="task-body">
            <!-- Task rows will be dynamically added here -->
        </tbody>
    </table>
    <div id="no-tasks" class="no-tasks">No tasks for the selected date.</div>
</div>

<div id="taskModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal();">&times;</span>
        <h2>Add Task</h2>
        <form id="taskForm" onsubmit="submitTask(event);">
            <label for="timeRange">Time Range:</label>
            <input type="text" id="timeRange" name="timeRange" required>
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
            <button type="submit" id="submitTaskBtn">Submit</button>
        </form>
    </div>
</div>

<div id="taskModalSuccess" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModalSuccess();">&times;</span>
        <h2>Success!</h2>
        <p id="successMessage"></p>
    </div>
</div>
<script>
    const userId = '<%= session.getAttribute("userId") %>';
    const contextPath = '<%= request.getContextPath() %>';
</script>

<script src="view/javascript/WelcomeScripts.js"></script>
</body>
</html>
