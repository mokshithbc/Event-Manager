<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Verification</title>
    <link rel="stylesheet" type="text/css" href="view/css/VerifyEmailStyles.css">
</head>
<body>
    <div class="container">
        <h2>A Verification email is sent to the email registered with <%= request.getAttribute("username") %></h2>
        <form action="<%=request.getContextPath()%>/ResendEmailServlet" method="post" onsubmit="disableResendButton(); localStorage.setItem('resendTime', new Date().getTime());">
            <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
            <button type="submit" id="resendButton">Re-send Email</button>
        </form>
        <%
            Boolean emailResent = (Boolean) session.getAttribute("emailResent");
            if (emailResent != null && emailResent) {
                out.println("<div class='success-message'>Email resent.</div>");
                session.removeAttribute("emailResent");
            }
        %>
        <form action="<%=request.getContextPath()%>/VerifyEmailServlet" method="post">
            <label for="userOtp">Enter Verification code:</label>
            <input type="password" id="userOtp" name="userOtp" required>
            <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
            <button type="submit">Verify</button>
            <br>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
                    out.println("<p class='error-message'>" + errorMessage + "</p>");
                }
            %>
        </form>
    </div>
    <script src="view/javascript/VerifyEmailScript.js"></script>
</body>
</html>
