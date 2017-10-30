<%@ page import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">

<link rel="stylesheet" href="gallery/sources/external/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="gallery/sources/external/fontawesome/css/font-awesome.css">
<link rel="stylesheet" href="gallery/sources/external/jquery-ui/jquery-ui.css">
<link rel="stylesheet" href="gallery/sources/css/site.css">
<link rel="stylesheet" href="gallery/sources/css/header.css">

<nav class="navbar navbar-inverse">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="view/MainPage.jsp">
            <i class="fa fa-video-camera" aria-hidden="true"></i><span style="padding-left: 15px;">501Gallery</span>
        </a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <form class="navbar-form navbar-left" action="SearchControl" method="post">
                <div class="form-group">
                    <input type="text" id="full-text-search" name="title" class="form-control" placeholder="...Image Name">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
            <li><a href="view/Search.jsp">Advanced Search</a></li>
        </ul>
    </div>
</nav>

<script src="gallery/sources/external/jquery/jquery-3.2.1.js"></script>
<script src="gallery/sources/external/jquery-ui/jquery-ui.js"></script>
