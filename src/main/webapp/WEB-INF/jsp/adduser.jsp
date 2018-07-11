<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>

<a href="/admin/dashboard">View Dashboard</a><br/>

<h1>Add New User</h1>
<form:form action="/admin/create_user" method="post" id="createUserForm" modelAttribute="userCreationRequestModel">
    <table align="center">
        <tr>
            <td><form:label path="userName">Username:</form:label></td>
            <td><form:input path="userName"/></td>
            <td><form:errors path="userName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First name:</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last name:</form:label></td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>

        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Add User"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>