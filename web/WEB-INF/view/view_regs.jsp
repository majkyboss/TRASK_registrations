<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="mainColumn">
    <a href="create_reg">add registration</a>
    <table border="1" class="mainContentTable">
        <tr id="table_header">
            <td>ICO</td>
            <td>Company name</td>
            <td>Registration date</td>
            <td>Creator</td>
            <td>Status</td>
        </tr>
        <c:forEach var="item" items="${registrations}">
            <tr class="table_row">
                <td>${item.registrationPK.ico}</td>
                <td>${item.companyName}</td>
                <td>
                    <fmt:formatDate value="${item.registrationPK.regDate}" pattern="dd.MM.yyyy" var="userDate" />
                    ${userDate}
                </td>
                <td>${item.unit.user.name}</td>
                <td>${item.regStateid.name}</td>
                <td>
                    <form action="edit_reg" method="POST">
                        <input type="hidden" name="ico" value="${item.registrationPK.ico}"/>
                        <fmt:formatDate value="${item.registrationPK.regDate}" pattern="dd.MM.yyyy-hh:mm:ss" var="userDate" />
                        <input type="hidden" name="reg_date" value="${userDate}"/>
                        <input type="submit" value="Edit"/>
                    </form>
                    <form action="del_reg" method="POST">
                        <input type="hidden" name="ico" value="${item.registrationPK.ico}"/>
                        <fmt:formatDate value="${item.registrationPK.regDate}" pattern="dd.MM.yyyy-hh:mm:ss" var="userDate" />
                        <input type="hidden" name="reg_date" value="${userDate}"/>
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

