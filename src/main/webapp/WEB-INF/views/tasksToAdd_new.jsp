<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%@ include file="links.jspf" %>
    <%--    <script src="http://code.jquery.com/jquery-1.9.0rc1.js"></script>--%>

    <%--    <script src="http://code.jquery.com/jquery-migrate-1.0.0rc1.js"></script>--%>
    <script src="js/slub.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/mmystyle.css">
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form:form method="post" modelAttribute="TaskToEvents">
        <h2><br><br><br></h2>
        <div class="card bg-light mb-3">
            <div class="card-header">
<%--                <div class="h-50">--%>
                    <c:choose>
                        <c:when test="${empty TaskToEvents.taskToEvents}">
                            <h5 class="card-title"> Wygląda, że wszystkie zadania są już uwzględnione
                                <a class="btn btn-success float-right" href="${header.referer}">Wróć</a></h5>
                        </c:when>
                        <c:otherwise>
                            <h5 class="card-title">Wybierz zadania do swojego wydarzenia
                                <input type="submit" value="Dodaj zaznaczone"
                                       class="btn btn-success float-sm-right mb-10"></h5>
                        </c:otherwise>
                    </c:choose>
<%--                </div>--%>

            </div>
            <div class="card-body">
                <table class="table table-hover" id="header">
                    <thead>
                    <tr>
                        <th class="col-sm-1 text-center">Id</th>
                        <th scope="col"  class="col-6 text-left">Opis</th>
                        <th scope="col" class="text-center">Wybierz
                                <%--                    <button class="btn btn-success" id="addBtn">Dodaj zadanie</button>--%>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach items="${taskGroups}" var="grupa">
                        <div>
                            <tr>
                                <th class="taskGroup" data-idg="${grupa.id}" data-arrow="${grupa.id}">▼</th>
                                <th class="taskGroup" data-idg="${grupa.id}">
                                    <c:out value="Grupa: ${grupa.description}"></c:out>
                                </th>
                                <th class="text-center"></th>
                            </tr>
                        </div>
                        <div>
                            <c:forEach items="${TaskToEvents.taskToEvents}" var="taskToEvent" varStatus="tagStatus">
                                <c:if test="${grupa.id eq taskToEvent.task.taskGroup.id}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <tr class="d-flex">
                                        <td class="text-right">
                                            <c:out value="${count}"></c:out>
                                                <%--                                <form:label path="taskToEvents[${tagStatus.index}].task.id"--%>
                                                <%--                                            value="${taskToEvent.task.id}">${taskToEvent.task.id}</form:label>--%>
                                            <form:hidden path="taskToEvents[${tagStatus.index}].task.id"/>
                                        </td>
                                        <td>
                                                <%--                        <c:out value="${task.task.description}"></c:out>--%>
                                            <form:label path="taskToEvents[${tagStatus.index}].task.description"
                                                        value="${taskToEvent.task.description}">${taskToEvent.task.description}</form:label>
                                        </td>
                                        <td class="text-center">
                                            <form:checkbox
                                                    path="taskToEvents[${tagStatus.index}].toAdd"></form:checkbox>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer">
                <c:if test="${not empty TaskToEvents.taskToEvents}">
                    <input type="submit" value="Dodaj zaznaczone" class="btn btn-success float-right mb-10">
                </c:if>
            </div>
        </div>
    </form:form>


</div>
<h2><br><br></h2>
<%@ include file="footer.jsp" %>
</body>
</html>
