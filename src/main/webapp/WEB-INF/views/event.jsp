<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
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
    <%@ include file="links.jspf" %>
    <%--    <script src="js/books.js"></script>--%>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form:form method="post" modelAttribute="event" id="edit-form">
        <div>
            <h3><br><br></h3>
        </div>
        <div class="card bg-light mb-3">

            <div class="card-header">
                <div class="row">
                    <div class="form-group col-md-6">
                        <c:choose>
                        <c:when test="${empty eventId}">
                            <label><h4>Najpierw uzupełnij dane ślubu:</h4>
                        </c:when>
                            <c:otherwise>
                        <label><h4>Szczegóły:</h4>
                            </c:otherwise>
                            </c:choose>
                            <c:if test="${empty venues}">
                                <a style="color:red; text-decoration: underline;" href="/venues/addtmp"><b>Brak
                                    lokalizacji! Dodaj nową lokalizację</b></a>
                            </c:if>
                            <c:if test="${venues.size()==1}">
                                <a style="color:red; text-decoration: underline;" href="/venues/addtmp"><b>Tylko jedna
                                    lokalizacja. Dodaj drugą lokalizację</b></a>
                            </c:if>
                        </label>


                    </div>
                        <%--                    <div class="form-group col-md-4">--%>
                        <%--                            &lt;%&ndash;                        <label>Test</label>&ndash;%&gt;--%>
                        <%--                    </div>--%>
                    <div class="form-group col-md-6">
                        <input type="submit" method="get" id="submitBtn" data-method="POST"
                               class="btn btn-success float-right"
                               value=" Zapisz ">
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label>Nazwa:</label><br>
                        <form:input path="name" placeholder="np. Ślub Agaty i Adama" id="formEventName"
                                    class="form-control"/>
                        <form:errors path="name" element="div" cssClass="error"></form:errors>
                    </div>


                        <%--        </div>--%>
                        <%--        <div class="row">--%>
                    <div class="form-group col-md-3">
                        <label>Imię panny młodej:</label><br>
                        <form:input path="bride" type="text" name="bride" placeholder="np. Agata" id="formBride"
                                    class="form-control"/>
                        <form:errors path="bride" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-3">
                        <label>Imię panna młodego:</label><br>
                        <form:input path="groom" type="text" name="groom" placeholder="np. Adam" id="formGroom"
                                    class="form-control"/>
                        <form:errors path="groom" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                    </div>
                    <div class="form-group col-md-3">
                        <label>Liczba gości Panny Młodej:</label><br>
                        <form:input path="brideGuestsNo" type="number" name="brideGuestsNo" id="formBrideGuestsNo"
                                    class="form-control"/>
                        <form:errors path="brideGuestsNo" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-3">
                        <label>Liczba gości Pana Młodego:</label><br>
                        <form:input path="groomGuestsNo" type="number" name="groomGuestsNo" id="formGroomGuestsNo"
                                    class="form-control"/>
                        <form:errors path="groomGuestsNo" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
            </div>

        </div>
        <%--        <div><br></div>--%>
        <div class="card bg-light mt-3">
            <div class="card-body">
                <div class="row">

                    <div class="form-group col-md-1">
                        <label><b>Ślub:</b>

                        </label>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="ceremonyVenue">Lokalizacja:</label><br>
                        <form:select id="ceremonyVenue" path="ceremonyVenue.id" items="${venues}"
                                     itemValue="id" itemLabel="fullName" class="form-control"/>
                        <form:errors path="ceremonyVenue" element="div" cssClass="error"/>
                        <a href="/venues/addtmp">Dodaj nową lokalizację</a>
                    </div>
                        <%--                </div>--%>
                        <%--                <div class="row">--%>

                    <div class="form-group col-md-3">
                        <label for="formCeremonyDate">Data:</label>
                        <form:input type="date" path="ceremonyDate" id="formCeremonyDate" class="form-control"/>
                        <form:errors path="ceremonyDate" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="formCeremonyTime">Godzina:</label><br>
                        <form:input path="ceremonyTime" type="time" name="ceremonyTime" placeholder="godzina"
                                    id="formCeremonyTime" class="form-control"/>
                        <form:errors path="name" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="formCeremonyType">Typ: </label><br>
                        <form:select path="type" type="text" name="type" placeholder="typ" id="formCeremonyType"
                                     class="form-control">
                            <%--                    <option selected value="">Wybierz...</option>--%>
                            <form:option value="0">nie wiem...</form:option>
                            <form:option value="1">kościelny</form:option>
                            <form:option value="2">cywilny</form:option>
                        </form:select>
                        <form:errors path="type" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
            </div>
        </div>
        <%--        <div><br></div>--%>
        <div class="card bg-light mb-3 mt-3">
            <div class="card-body">
                <div class="row">
                    <div class="form-group col-md-1">
                        <label for="formPartyDate"><b>Wesele:</b>
                        </label>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="partyVenue">Lokalizacja:</label><br>
                        <form:select id="partyVenue" path="partyVenue.id" items="${venues}"
                                     itemValue="id" itemLabel="fullName" class="form-control"/>
                        <form:errors path="partyVenue" element="div" cssClass="error"/>
                        <a href="/venues/addtmp">Dodaj nową lokalizację</a>
                    </div>
                        <%--                </div>--%>
                        <%--                <div class="row">--%>
                    <div class="form-group col-md-3">
                        <label for="formPartyDate">Data:</label><br>
                        <form:input type="date" path="partyDate" placeholder="data" id="formPartyDate"
                                    class="form-control"/>
                        <form:errors path="partyDate" element="div" cssClass="error"></form:errors>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="formPartyTime">Godzina:</label><br>
                        <form:input path="partyTime" type="time" name="partyTime" placeholder="godzina"
                                    id="formPartyTime"
                                    class="form-control"/>
                        <form:errors path="partyTime" element="div" cssClass="error"></form:errors>
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
