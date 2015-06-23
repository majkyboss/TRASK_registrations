<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="mainColumn">

    <form action="add_reg" method="POST">
        <table border="1" class="mainContentTable">
            <tr><td><label>ICO</label></td><td><input type="text" name="ico"/></td></tr>
            <tr><td><label>Company name</label></td><td><input type="text" name="companyName"/></td></tr>
            <tr><td>Registration date</td>
                <td>[today]</td></tr>
            <tr><td>Creator</td>
                <td>
                    <select name="user">
                        <c:forEach var="item" items="${users}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td></tr>
            <tr><td><label>Status</label></td>
                <td>
                    <select name="status">
                        <c:forEach var="item" items="${statuses}">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td></tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </form>

</div>