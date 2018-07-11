<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<body>
Hello admin - ${dashboardModel.admin.userName}

<div>
    <a href="/admin/update_user/${dashboardModel.admin.id}"> Edit profile</a>
</div>

<a href="/admin/create_user">Add User</a>
<c:if test="${not empty dashboardModel.users}">

    <ul>
        <c:forEach var="user" items="${dashboardModel.users}">
            <li>${user.userName}
                <span>${user.firstName}</span>
                <span>${user.lastName}</span>
                <span><a href="/admin/delete_user/${user.id}">Remove user</a></span>
                <span><a href="/admin/update_user/${user.id}">Update user</a></span>
            </li>
        </c:forEach>
    </ul>

</c:if>

</body>
</html>