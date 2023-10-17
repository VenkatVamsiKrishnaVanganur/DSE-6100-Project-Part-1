<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login to Database</title>
    <style>
        body {
            text-align: center;
        }
        h1 {
            text-align: center;
        }
        .center-align {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h1>Welcome to Login page</h1>
    <p>${loginStr}</p>
    <div class="center-align">
        <form action="login" method="post">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Username:</th>
                    <td>
                        <input type="text" name="email" size="45" autofocus>
                    </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td>
                        <input type="password" name="password" size="45">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login" />
                    </td>
                </tr>
            </table>
            <a href="register1.jsp" target="_self">Register Here</a>
        </form>
    </div>
</body>
</html>
