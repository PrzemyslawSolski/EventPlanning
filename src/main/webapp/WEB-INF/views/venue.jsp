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
    <link rel="stylesheet" type="text/css" href="/css/mmystyle.css">
    <%--    <script src="js/books.js"></script>--%>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form:form id="edit-form" method="post" modelAttribute="venue">
        <div>
            <h3><br><br><br></h3>
<%--            <h3>Szczegóły</h3><br>--%>
        </div>
        <div class="row">

            <div class="form-group col-md-6">
                <label><h3>Adres:</h3></label>
<%--                <input type="text" placeholder="np. Ślub Agaty i Adama" id="formAddress" class="form-control">--%>
            </div>
        </div>


        <div class="row">

            <div class="form-group col-md-6">
                <label>Miejsce:</label>
                <form:input path="name" name="place" placeholder="np. kościół NMP" id="formVenueName" class="form-control"/>
                <form:errors path="name" element="div" cssClass="error"></form:errors>
            </div>

        </div>
        <div class="row">

            <div class="form-group col-md-3">
                <label>Miejscowość:</label>
                <form:input path="city" type="text" name="city" placeholder="np. Pcim" id="formEventCity" class="form-control"/>
                <form:errors path="city" element="div" cssClass="error"></form:errors>
            </div>
            <div class="form-group col-md-2">
                <label>Kod pocztowy:</label>
                <form:input path="zip" name="zip" pattern="^[0-9]{2}-[0-9]{3}$" oninput="if(this.value.length==2 && this.value.indexOf('-')==-1) this.value+='-';" placeholder="np. 02-345" id="formEventZip" class="form-control"/>
                <form:errors path="zip" element="div" cssClass="error"></form:errors>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-3">
                <label for="formVenueStreet" >Ulica:</label>
                <form:input path="street" name="street" placeholder="np. Grochowska" id="formVenueStreet" class="form-control"/>
                <form:errors path="street" element="div" cssClass="error"></form:errors>
            </div>
            <div class="form-group col-md-2">
                <label for="formVenueNumber" >Numer:</label>
                <form:input path="number" name="number" placeholder="np. 3 lub 34/16" id="formVenueNumber" class="form-control"/>
                <form:errors path="number" element="div" cssClass="error"></form:errors>
            </div>
        </div>
<%--        <div class="row">--%>
<%--            <div class="form-group col-md-2">--%>
<%--                <label for="formEventType1" >Typ:</label>--%>
<%--                <select type="date" placeholder="data" id="formEventType1" class="form-control">--%>
<%--                    <option>kościelny</option>--%>
<%--                    <option>cywilny</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="row">--%>
<%--            <div class="form-group col-md-6">--%>
<%--                <label>Publisher</label>--%>
<%--                <input type="text" placeholder="Podaj isbn" id="publisher" class="form-control">--%>
<%--            </div>--%>
<%--            <div class="form-group col-md-6">--%>
<%--                <label hidden>Typ</label>--%>
<%--                <input type="text" placeholder="Podaj typ" id="backup" hidden class="form-control">--%>
<%--            </div>--%>
<%--        </div>--%>
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
