<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: psolski
  Date: 25.10.2019
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
    <%@ include file="links.jspf" %>
    <%--    <script src="http://code.jquery.com/jquery-1.9.0rc1.js"></script>--%>

    <%--    <script src="http://code.jquery.com/jquery-migrate-1.0.0rc1.js"></script>--%>
    <script src="js/books.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/mmystyle.css">
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container">

    <div>
        <h2><br><br></h2>
    </div>

    <div class="card bg-light mb-3">
        <div class="card-header">
            <h4>Lista lokalizacji</h4>
            <c:if test="${empty venues}">
                <a href="/venues/add"> Dodaj przynajmniej jedną lokalizację (Ślub->Dodaj lokalizację)</a>
            </c:if>

        </div>
        <div class="card-body">
            <table class="table table-hover" id="header">
                <thead>
                <tr class="d-flex">
                    <th class="col-sm-1 text-center">Lp.</th>
                    <th class="col-5">Nazwa</th>
                    <th class="col-5 text-center">Adres</th>
                    <%--                    <th class="col-2 text-center">Adres</th>--%>
                    <th class="col-1"></th>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:set var="count" value="0" scope="page"/>
                <c:forEach items="${venues}" var="venue">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr class="d-flex">
                        <td class="col-sm-1 text-right">
                                <%--                    <c:out value="${task.id}"></c:out>--%>
                            <c:out value="${count}"></c:out>
                        </td>
                        <td class="col-5"><c:out value="${venue.name}"></c:out></td>
                        <td class="col-5 text-center"><c:out value="${venue.address}"></c:out></td>

                        <td class="col-1 text-center"><a href="/venues/edit/${venue.id}">Zmień</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<h2><br><br></h2>
<%@ include file="footer.jsp" %>
</body>
</html>
