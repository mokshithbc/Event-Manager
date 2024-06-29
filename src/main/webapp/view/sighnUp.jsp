<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/SighnUpStyles.css">
</head>
<body>
    <div class="container">
        <h1>Sign Up</h1>
        <form action="<%=request.getContextPath()%>/SighnUpServlet" method="post">
            <label for="txtMail">Email:</label>
            <input type="email" id="txtMail" name="txtMail" required>
            <label for="txtName">Enter Username:</label>
            <input type="text" id="txtName" name="txtName" required>
            <label for="txtPwd">Enter Password:</label>
            <input type="password" id="txtPwd" name="txtPwd" required>
            <label for="txtConfirmPwd">Confirm Password:</label>
            <input type="password" id="txtConfirmPwd" name="txtConfirmPwd" required>
            <div>
                <input type="submit" value="Sign Up">
                <input type="reset" value="Reset">
                or<span class="login-link"> <a href="<%=request.getContextPath()%>/view/login.jsp">Sign in?</a></span>
            </div>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
                    out.println("<p class='error-message'>" + errorMessage + "</p>");
                }
                String accountExists = (String) request.getAttribute("accountExist");
                if (accountExists != null) {
                    out.println("<p class='error-message'>" + accountExists + "</p>");
                }
            %>
        </form>
    </div>
</body>
</html>
