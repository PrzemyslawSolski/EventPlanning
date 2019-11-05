<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: psolski
  Date: 27.10.2019
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
</head>
<body>

<%@ include file="header.jsp" %>
<%--<div>--%>
<%--    <h2><br><br></h2>--%>
<%--    <h3>login</h3><br>--%>
<%--</div>--%>

<div class="d-flex justify-content-center align-items-center container">

    <form id="edit-form">
        <div>
            <h2><br><br></h2>
        </div>
        <div class="row">
            <div class="form-group col-md-10">
                <label><h3>Zaloguj się</h3></label>
            </div>

        </div>


        <div class="row">
            <div class="form-group col-md-10">
                <label>Podaj email:</label>
                <input type="text" placeholder="adres email" id="formEmail" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-10">
                <label>Podaj hasło:</label>
                <input type="password" placeholder="hasło" id="formPassword" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="formShowPass">
                <label class="form-check-label" for="formShowPass">
                    Pokaż hasło
                </label>
            </div>
        </div>
        <input type="submit" method="post" id="submitBtn" data-method="POST" class="btn btn-success" value="Zaloguj">
        <h2><br><br></h2>
    </form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
