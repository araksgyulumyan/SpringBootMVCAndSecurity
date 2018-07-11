<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<body>
<a href="/admin/dashboard">Go to admin dashboard</a>


<div>First name ${createdUserModel.firstName}</div>
<div>Last name ${createdUserModel.lastName}</div>
<div>User name ${createdUserModel.userName}</div>
</body>
</html>