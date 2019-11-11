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
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container">

    <div>
        <h2><br><br></h2>
    </div>

    <div class="card bg-light mb-3">
        <div class="card-header">
            <h4>Kosztorys</h4>
        </div>
        <div class="card-body">
            <div class="card bg-light">
                <div class="container">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label><h5>Łączna kwota: ${estimate.total} zł</h5></label>
                        </div>
                        <div class="form-group col-md-4"><label>
                            <h5>z tego zapłacone: ${estimate.totalPaid} zł</h5>
                        </label></div>
                        <div class="form-group col-md-4"><label>
                            <h5>do zapłaty: ${estimate.total - estimate.totalPaid} zł</h5>
                        </label></div>
                    </div>
                </div>
                <div class="card-header">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label>Panna Młoda: ${estimate.brideSubtotal} zł</label>
                        </div>
                        <div class="form-group col-md-4"><label>
                            Pan Młody: ${estimate.groomSubtotal} zł
                        </label></div>
                        <div class="form-group col-md-4"><label>
                            Nie podzielone: ${estimate.notSplit} zł
                        </label></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header">
            <h4>Najbliższe płatności</h4>
        </div>
        <div class="card-body">
            <table class="table table-hover" id="header">
                <thead>
                <tr class="d-flex">
                    <th class="col-sm-1 text-center">Lp.</th>
                    <th class="col-6">Zadanie</th>
                    <th class="col-2 text-center">Termin</th>
                    <th class="col-2 text-center">Kwota</th>
                    <th class="col-1"></th>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:set var="count" value="0" scope="page"/>
                <c:forEach items="${estimateTasks}" var="task">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr class="d-flex">
                        <td class="col-sm-1 text-right">
                                <%--                    <c:out value="${task.id}"></c:out>--%>
                            <c:out value="${count}"></c:out>
                        </td>
                        <td class="col-6"><c:out value="${task.task.description}"></c:out></td>
                        <td class="col-2 text-center"><c:out value="${task.date}"></c:out></td>
                        <td class="col-2 text-center"><c:out
                                value="${task.price.amount - task.price.amountPaid}"></c:out>

                        </td>
                        <td class="col-1 text-center"><a href="/tasks/edit/${task.id}">Zmień</a></td>
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
