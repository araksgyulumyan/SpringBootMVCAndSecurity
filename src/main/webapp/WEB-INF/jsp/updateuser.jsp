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
    </style>

</head>
<body>

<a href="/admin/dashboard">View Dashboard</a><br/>

<h1>Update User</h1>
<form:form action="/admin/update_user/${userUpdateRequestModel.id}" method="put" id="updateUserForm"
           modelAttribute="userUpdateRequestModel">
    <table align="center">
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
            <td><input type="submit" value="Update User"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>