<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar fixed-top navbar-expand-md navbar-dark bg-dark">
    <a id="navbarName" class="navbar-brand" href="/">Ślub@wanie</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <c:if test="${empty userId}">
                    <a id="menuTaskList" class="nav-link" href="/tasks">Przykładowa lista zadań <span class="sr-only">(current)</span></a>
                </c:if>
                <c:if test="${not empty userId}">
                    <a id="menuTaskList" class="nav-link" href="/tasks/list">Harmonogram<span
                            class="sr-only">(current)</span></a>
                </c:if>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Rejestracja</a>
            </li>
            <li class="nav-item">
                <c:if test="${empty userId}">
                    <a class="nav-link" href="/login">Logowanie</a>
                </c:if>
                <c:if test="${not empty userId}">
                    <a class="nav-link" href="/logout">Wyloguj</a>
                </c:if>
            </li>
            <c:if test="${not empty userId}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Ślub
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/events/edit/">Szczegóły</a>
                        <a class="dropdown-item" href="/tasks/estimate">Kosztorys</a>
                        <a class="dropdown-item" href="/events/addTasks">Dodawanie zadań z listy programu</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/tasks/add">Dodaj swoje zadanie</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/venues/list">Lista lokalizacji</a>
                        <a class="dropdown-item" href="/venues/add">Dodaj lokalizację</a>
                    </div>
                </li>
            </c:if>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="/logout">Wyloguj</a>--%>
<%--            </li>--%>
            <li class="nav-item">
                <div class="nav-link active"> User: ${userId} ${firstName} Event: ${eventId}</div>
            </li>
        </ul>
        <%--        <form class="form-inline my-2 my-lg-0">--%>
        <%--            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">--%>
        <%--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
        <%--        </form>--%>
    </div>
</nav>