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
    <%@ include file="links.jspf" %>
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
