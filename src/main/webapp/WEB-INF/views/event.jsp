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
    <meta charset="UTF-8">
    <title>Ślub/owanie</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <%--    <script src="js/books.js"></script>--%>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form:form method="post" modelAttribute="event" id="edit-form">
        <div>
            <h3><br><br></h3>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label><h3>Szczegóły ślubu:</h3></label>
            </div>

        </div>


        <div class="row">
            <div class="form-group col-md-6">
                <label>Nazwa:</label><br>
                <form:input path="name" placeholder="np. Ślub Agaty i Adama" id="formEventName" class="form-control"/>
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

            <div class="form-group col-md-1">
                <label><b>Ślub:</b>
                        <%--                //*TODO dodać listę lokalizacji--%>

                </label>
            </div>

            <div class="form-group col-md-1"></div>
            <div class="form-group col-md-4">
                <label for="ceremonyVenue">Miejsce:</label><br>
                <form:select id="ceremonyVenue" path="ceremonyVenue.id" items="${venues}"
                             itemValue="id" itemLabel="fullName" class="form-control"/>
                <form:errors path="ceremonyVenue" element="div" cssClass="error"/>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-2">
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
        <div class="row">
            <div class="form-group col-md-1">
                <label for="formPartyDate"><b>Wesele:</b>


                </label>
                    <%--                    //*TODO dodać listę lokalizacji--%>
            </div>

            <div class="form-group col-md-1"></div>
            <div class="form-group col-md-4">
                <label for="partyVenue">Miejsce:</label><br>
                <form:select id="partyVenue" path="partyVenue.id" items="${venues}"
                             itemValue="id" itemLabel="fullName" class="form-control"/>
                <form:errors path="partyVenue" element="div" cssClass="error"/>
            </div>
                        </div>
                        <div class="row">
            <div class="form-group col-md-2">
                <label for="formPartyDate">Data:</label><br>
                <form:input type="date" path="partyDate" placeholder="data" id="formPartyDate" class="form-control"/>
                <form:errors path="partyDate" element="div" cssClass="error"></form:errors>
            </div>
            <div class="form-group col-md-2">
                <label for="formPartyTime">Godzina:</label><br>
                <form:input path="partyTime" type="time" name="partyTime" placeholder="godzina" id="formPartyTime"
                            class="form-control"/>
                <form:errors path="partyTime" element="div" cssClass="error"></form:errors>
            </div>
        </div>
        <label for="submitBtn"></label>
        <input type="submit" method="get" id="submitBtn" data-method="POST" class="btn btn-success" value=" Zapisz ">
        <br><br>
    </form:form>

    <%--    <div>--%>
    <%--        <h2><br><br><br></h2>--%>
    <%--        <h2>Lista książek</h2><br>--%>
    <%--    </div>--%>

    <%--    <table class="table table-hover" id="header">--%>
    <%--        <thead>--%>
    <%--        <tr>--%>
    <%--            <th>Id</th>--%>
    <%--            <th>Autor</th>--%>
    <%--            <th>Tytuł</th>--%>
    <%--            <th>--%>
    <%--                <button class="btn btn-success" id="addBtn">Dodaj książkę</button>--%>
    <%--            </th>--%>
    <%--        </tr>--%>
    <%--        </thead>--%>
    <%--        <tbody id="tBody">--%>

    <%--        </tbody>--%>
    <%--    </table>--%>


</div>
<h2><br><br></h2>

<%@ include file="footer.jsp" %>

</body>
</html>
