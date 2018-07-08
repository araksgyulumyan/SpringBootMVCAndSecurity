<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<body>
Hello admin - ${dashboardModel.adminUserName}

<%--todo add list view of all users --%>

<c:if test="${not empty dashboardModel.users}">

    <ul>
        <c:forEach var="user" items="${dashboardModel.users}">
            <li>${user.userName}</li>
        </c:forEach>
    </ul>

</c:if>

</body>
</html>