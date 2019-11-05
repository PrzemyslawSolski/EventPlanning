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
    <title>Lista książek</title>
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
    <link href='<c:url value="/css/mystyle.css"/>' rel="stylesheet" type="text/css">
    <%--    <script src="http://code.jquery.com/jquery-1.9.0rc1.js"></script>--%>

    <%--    <script src="http://code.jquery.com/jquery-migrate-1.0.0rc1.js"></script>--%>
    <script src="js/books.js"></script>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form id="edit-form">
        <div>
            <h2><br><br><br></h2>
            <h2>Dodaj książkę</h2><br>
        </div>


        <div class="row">
            <div class="form-group col-md-6">
                <label>Autor:</label>
                <input type="text" placeholder="Podaj autora" id="formAuth" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label>Tytuł:</label>
                <input type="text" placeholder="Podaj tytuł" id="title" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label>ISBN</label>
                <input type="text" placeholder="Podaj isbn" id="isbn" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label>Typ</label>
                <input type="text" placeholder="Podaj typ" id="type" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label>Publisher</label>
                <input type="text" placeholder="Podaj isbn" id="publisher" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label hidden>Typ</label>
                <input type="text" placeholder="Podaj typ" id="backup" hidden class="form-control">
            </div>
        </div>
        <input type="submit" id="submitBtn" data-method="POST" class="btn btn-success">
        <br><br>
    </form>

    <div>
        <h2><br><br><br></h2>
        <h2>Lista książek</h2><br>
    </div>

    <table class="table table-hover" id="header">
        <thead>
        <tr>
            <th>Id</th>
            <th>Autor</th>
            <th>Tytuł</th>
            <th>
                <button class="btn btn-success" id="addBtn">Dodaj książkę</button>
            </th>
        </tr>
        </thead>
        <tbody id="tBody">

        </tbody>
    </table>




</div>
<h2><br><br></h2>
<%@ include file="footer.jsp" %>
</body>
</html>
