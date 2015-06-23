<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="mainColumn">

    <form action="save_reg" method="POST">
        <table border="1" class="mainContentTable">
            <input type="hidden" name="icoOrigin" value="${registration.registrationPK.ico}"/>
            <tr><td><label>ICO</label></td><td><input type="text" name="companyName" value="${registration.registrationPK.ico}"/></td></tr>
            <tr><td><label>Company name</label></td><td><input type="text" name="companyName" value="${registration.companyName}"/></td></tr>
            <tr><td>Registration date</td>
                <td><fmt:formatDate value="${registration.registrationPK.regDate}" pattern="dd.MM.yyyy" var="userDate" />
                    ${userDate}
                    <fmt:formatDate value="${registration.registrationPK.regDate}" pattern="dd.MM.yyyy-hh:mm:ss" var="userDate" />
                    <input type="hidden" name="regDateOrigin" value="${userDate}"/>
                </td></tr>
            <tr><td>Creator</td><td>${registration.unit.user.name}</td></tr>
            <tr><td><label>Status</label></td><td>${registration.regStateid.name}</td></tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </form>

</div>