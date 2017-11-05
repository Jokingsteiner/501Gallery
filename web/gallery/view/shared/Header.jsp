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
        <a class="navbar-brand" href="GalleryControl">
            <i class="fa fa-video-camera" aria-hidden="true"></i><span style="padding-left: 15px;">501Gallery</span>
        </a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <form class="navbar-form navbar-left" action="SearchControl" method="get">
                <input type="hidden" name="forImage" value="true">
                <div class="form-group">
                    <input type="text" name="type" class="form-control" size="10%" placeholder="...Image type">
                </div>
                <div class="form-group">
                    <input type="text" name="artist_name" class="form-control" size="10%" placeholder="...Artist Name">
                </div>
                <div class="form-group">
                    <input type="text" name="location" class="form-control" size="10%" placeholder="...Location">
                </div>
                <div class="form-group">
                    <input type="text" name="year_start" class="form-control" size="10%" placeholder="...Start Year">
                </div>
                <div class="form-group">
                    <input type="text" name="year_end" class="form-control" size="10%" placeholder="...End Year">
                </div>
                <button type="submit" class="btn btn-default">Image</button>
            </form>

            <form class="navbar-form navbar-left" action="SearchControl" method="get">
                <input type="hidden" name="forArtist" value="true">
                <div class="form-group">
                    <input type="text" name="country" class="form-control" size="10%" placeholder="...Country">
                </div>
                <div class="form-group">
                    <input type="text" name="birth_year" class="form-control" size="10%" placeholder="...Birth Year">
                </div>
                <button type="submit" class="btn btn-default">Artist</button>
            </form>

            <li><a href="gallery/view/AddGalleryArtist.jsp">Insert Info</a></li>
            <li><a href="gallery/view/AddImage.jsp">Insert Image</a></li>
        </ul>
    </div>
</nav>

<script src="gallery/sources/external/jquery/jquery-3.2.1.js"></script>
<script src="gallery/sources/external/jquery-ui/jquery-ui.js"></script>
