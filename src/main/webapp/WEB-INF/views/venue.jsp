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

    <form id="edit-form">
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
                <input type="text" name="place" placeholder="np. kościół NMP" id="formVenueName" class="form-control">
            </div>

        </div>
        <div class="row">

            <div class="form-group col-md-3">
                <label>Miejscowość:</label>
                <input type="text" name="city" placeholder="np. Pcim" id="formEventCity" class="form-control">
            </div>
            <div class="form-group col-md-2">
                <label>Kod pocztowy:</label>
                <input type="text" name="zip" pattern="^[0-9]{2}-[0-9]{3}$" oninput="if(this.value.length==2 && this.value.indexOf('-')==-1) this.value+='-';"placeholder="np. 02-345" id="formEventZip" class="form-control">
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-3">
                <label for="formVenueStreet" >Ulica:</label>
                <input type="text" name="street" placeholder="np. Grochowska" id="formVenueStreet" class="form-control">
            </div>
            <div class="form-group col-md-2">
                <label for="formVenueNumber" >Numer:</label>
                <input type="text" name="number" placeholder="np. 3 lub 34/16" id="formVenueNumber" class="form-control">
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
    </form>

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
