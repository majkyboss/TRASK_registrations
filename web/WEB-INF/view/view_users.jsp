<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="mainColumn">
    <table border="1" class="mainContentTable">
        <tr id="table_header">
            <td>ID</td>
            <td>Name</td>
            <td>Last name</td>
            <td>Degree</td>
            <td>Birth number</td>
            <td>Work started</td>
            <td>Work finished</td>
            <td>Role</td>
        </tr>
        <c:forEach var="item" items="${users}">
            <tr class="table_row">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.lastname}</td>
                <td>${item.degree}</td>
                <td>${item.birthNumber}</td>
                <td>
                    <fmt:formatDate value="${item.dateIn}" pattern="dd.MM.yyyy" var="userDate" />
                    ${userDate}
                </td>
                <td>
                    <fmt:formatDate value="${item.dateOut}" pattern="dd.MM.yyyy" var="userDate" />
                    ${userDate}
                </td>
                <td>${item.roleId.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>