<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/css/LoginStyles.css">
</head>
<body>
    <div class="container">
        <h1>User Login</h1>
        <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
            <label for="txtName">Enter UserName:</label>
            <input type="text" id="txtName" name="txtName" required>
            <label for="txtPwd">Enter Password:</label>
            <input type="password" id="txtPwd" name="txtPwd" required>
            <div>
                <input type="submit" value="Login">
                <input type="reset" value="Reset">
                <span class="signup-link">or <a href="<%=request.getContextPath()%>/view/sighnUp.jsp">Create an account</a></span>
            </div>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
                    out.println("<p class='error-message'>" + errorMessage + "</p>");
                }
            %>
        </form>
    </div>
</body>
</html>
