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

<%--    <link rel="stylesheet" href="./css/mystyle.css">--%>
    <%@ include file="links.jspf" %>
    <style>
        body {
            background-image: url("/img/slubsil-16.JPG");
            background-size: cover;
        }
    </style>

    <%--    <script src="http://code.jquery.com/jquery-1.9.0rc1.js"></script>--%>

    <%--    <script src="http://code.jquery.com/jquery-migrate-1.0.0rc1.js"></script>--%>
<%--    <script src="js/slub.js"></script>--%>
</head>
<body>


<%@ include file="header.jsp" %>


<section class="padding-large bg-dark">
    <h2><br></h2>
    <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
        <div class="carousel-inner container">
            <div class="carousel-item active">
                    <div class="carousel-caption d-block">
                        <img src="/img/1m.jpg" width="1000px">
                    </div>
            </div>
<%--            <div class="carousel-item">--%>
<%--                    <div class="carousel-caption d-block">--%>
<%--                        <img src="/img/2m.jpg" width="1000px">--%>
<%--                    </div>--%>
<%--            </div>--%>
            <div class="carousel-item">
                    <div class="carousel-caption d-block">
                        <img src="/img/3m.jpg" width="1000px">
                    </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/4m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/16m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/17m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/88m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/109m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/112m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/254m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/257m.jpg" width="1000px">
                </div>
            </div>
            <div class="carousel-item">
                <div class="carousel-caption d-block">
                    <img src="/img/537m.jpg" width="1000px">
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>

<%--<h2><br><br></h2>--%>
<%@ include file="footer.jsp" %>
</body>
</html>
