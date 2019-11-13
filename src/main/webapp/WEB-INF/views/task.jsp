<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
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
    <%--    <script src="js/books.js"></script>--%>
    <style>
        .card {
        }
    </style>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form:form method="post" modelAttribute="eventTask" id="edit-form">
        <div>
            <h3><br><br></h3>
        </div>
        <div class="card bg-light mb-3">

            <div class="card-header">
                <div class="row">
                    <div class="form-group col-md-9">
                        <c:choose>
                            <c:when test="${eventTask.id==0}">
                                <label for="formDescription">Zadanie:</label><br>
                                <form:input type="text" path="task.description" id="formDescription"
                                            class="form-control"></form:input>
                                <form:errors path="date" element="div" cssClass="error"></form:errors>
                            </c:when>
                            <c:otherwise>
                                <label><h4>${eventTask.task.description}</h4></label>
                            </c:otherwise>
                        </c:choose>
                    </div>
                        <%--                    <div class="form-group col-md-4">--%>
                        <%--                            &lt;%&ndash;                        <label>Test</label>&ndash;%&gt;--%>
                        <%--                    </div>--%>
                    <div class="form-group col-md-2">
                        <input type="submit" id="submitBtn" data-method="POST"
                               class="btn btn-success float-right"
                               value=" Zapisz ">

                    </div>
                    <div class="form-group col-md-1">
                        <a class="btn btn-success float-right" href="${header.referer}">Wróć</a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="form-group col-md-1">
                    </div>
                        <%--        </div>--%>
                        <%--        <div class="row">--%>
                    <div class="form-group col-md-2">
                        <p/>
                        <p/>
                        <form:radiobutton path="completed" value="0"></form:radiobutton>planowane
                        <p/>
                        <form:radiobutton path="completed" value="1"></form:radiobutton>wykonane
                    </div>
                    <div class="form-group col-md-3">
                        <label for="formDate">Data:</label><br>
                        <form:input type="date" path="date" id="formDate" class="form-control"/>
                        <form:errors path="date" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-1">
                        <form:hidden path="task.id"></form:hidden>
                        <form:hidden path="event.id"></form:hidden>
                        <form:hidden path="price.id"></form:hidden>
                        <input type="hidden" name="referer" id="formReferer" class="form-control" value="${header.referer}">
                    </div>
                </div>
            </div>
        </div>
        <div class="card bg-light mt-3">
            <div class="card-body">
                <div class="row">
                    <div class="form-group col-md-1">
                    </div>
                    <div class="form-group col-md-2">
                        <label>Cena:</label><br>
                        <form:input path="price.amount" type="text" name="amount"
                                    id="formAmount"
                                    class="form-control"/>
                        <form:errors path="price.amount" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-2">
                        <p/>
                        <p/>
                        <form:radiobutton path="price.type" value="0"></form:radiobutton>szacowana
                        <p/>
                        <form:radiobutton path="price.type" value="1"></form:radiobutton>potwierdzona
                    </div>

                    <div class="form-group col-md-3">
                        <label for="formCeremonyType">Podział w kosztorysie: </label><br>
                        <form:select path="price.split" type="text" name="type" placeholder="typ" id="formCeremonyType"
                                     class="form-control">
                            <%--                    <option selected value="">Wybierz...</option>--%>
                            <form:option value="0">nie wiem...</form:option>
                            <form:option value="1">całość Pani młoda</form:option>
                            <form:option value="2">całość Pan młody</form:option>
                            <form:option value="3">równy podział</form:option>
                            <form:option value="4">podział liczbą gości</form:option>
                        </form:select>
                        <form:errors path="price.split" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-2">
                        <label>Kwota zapłacona:</label><br>
                        <form:input path="price.amountPaid" type="text" name="amountPaid"
                                    id="formAmountPaid"
                                    class="form-control"/>
                        <form:errors path="price.amountPaid" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
            </div>
        </div>
    </form:form>


</div>
<h2><br><br></h2>

<%@ include file="footer.jsp" %>

</body>
</html>
