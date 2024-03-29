<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" type="text/css" href="/css/mmystyle.css">
</head>
<body>

<%@ include file="header.jsp" %>
<%--<div>--%>
<%--    <h2><br><br></h2>--%>
<%--    <h3>login</h3><br>--%>
<%--</div>--%>

<div class="d-flex justify-content-center align-items-center container">

    <form:form method="post" id="edit-form" modelAttribute="user">
        <div>
            <h2><br><br></h2>
        </div>
        <div class="card bg-light mb-3">
            <div class="card-header">
                <div class="row">
                    <div class="form-group col-md-10">
                        <label><h3>Zaloguj się</h3></label>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label>Podaj email:</label>
                        <form:input path="email" type="text" placeholder="adres email" id="formEmail"
                                    class="form-control"/>
                        <form:errors path="email" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label>Podaj hasło:</label>
                        <form:input path="password" type="password" placeholder="hasło" id="formPassword"
                                    class="form-control password"/>
                        <form:errors path="password" element="div" cssClass="error"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                            <%--                <input class="form-check-input" type="checkbox" id="formShowPass">--%>
                            <%--                <label class="form-check-label" for="formShowPass">--%>
                            <%--                    Pokaż hasło--%>
                            <%--                </label>--%>
                    </div>
                </div>
                <input type="submit" method="post" id="submitBtn" data-method="POST" class="btn btn-success float-right"
                       value="Zaloguj">
            </div>
        </div>
        <h2><br><br></h2>
    </form:form>
</div>


<%@ include file="footer.jsp" %>
</body>
</html>
