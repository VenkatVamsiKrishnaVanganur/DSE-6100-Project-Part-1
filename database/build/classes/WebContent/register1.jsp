<!DOCTYPE html>
<html>
<head>
<title>Welcome to Registration Page</title> 
<style>
        body {
            background: #007BFF; /* Blue color code for the body background */
        }

        .registration-form {
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            width: 400px;
            margin: 0 auto;
        }

        table {
            width: 100%;
            background: #f2f2f2; /* Background color for the table */
        }

        table, th, td {
            border: 1px solid #000;
        }

        th, td {
            padding: 10px; /* Adjusted padding */
            text-align: center;
        }

        input[type="text"],
        input[type="email"] {
            width: 95%; /* Adjusted width */
            padding: 10px;
            margin: 5px 0;
            background: #f9f9f9; /* Background color for text inputs */
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
	<div align="center" class="registration-form"> <!-- Added class to apply styles -->
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="register">
			<table border="1" cellpadding="5">
				<tr>
					<th>Username: </th>
					<td align="center" colspan="3">
						<input type="text" name="email" value="example@gmail.com" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>First Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="firstName" value="FirstName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Last Name: </th>
					<td align="center" colspan="3">
						<input type="text" name="lastName" value="LastName" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="password" value="password" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" value="password" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
			    <th>Role:</th>
			    <td align="center" colspan="3">
			        <select name="role" id="role">
			            <option value="David Smith">David Smith</option>
			            <option value="Client">Client</option>
			            <option value="Admin Root">Admin Root</option>
			        </select>
			    </td>
			</tr>
					<td align="center" colspan="5">
						<input type="submit" value="Register"/>
					</td>
				</tr>
			</table>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>
</html>
