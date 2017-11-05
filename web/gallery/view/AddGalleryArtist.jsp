<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Add Gallery & Artist</title>
    </head>
    <body>
    <jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Header.jsp" />
    <div class="container">
        <% if(request.getAttribute("error") != null) { %>
            <% if((Boolean) request.getAttribute("error")) { %>
                <div class="alert alert-danger">
                    <%=request.getAttribute("message")%>
                </div>
            <% } else { %>
                <div class="alert alert-success">
                    <%=request.getAttribute("message")%>
                </div>
            <% } %>
        <% } %>

        <h1 align="center" class="my-title">Add Gallery</h1>
        <form action="gallery/AddGalleryControl" method="post">
            <div class="form-group">
                <label>Gallery Name:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" name="name" class="form-control" placeholder="...Gallery Name">
                </div>
            </div>

            <div class="form-group">
                <label>Description:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="description" class="form-control" style= "height: 100px;" placeholder="...Description">
                </div>
            </div>
            <br />
            <button class="btn btn-default" type="submit"><i class="fa fa-search"></i> Submit</button>
            <button class="btn btn-default" type="reset"><i class="fa fa-refresh"></i> Reset</button>
        </form>

        <h1 align="center" class="my-title">Add Artist</h1>
        <form action="gallery/AddArtistControl" method="post">
            <div class="form-group">
                <label>Artist Name:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" name="name" class="form-control" placeholder="...Artist Name">
                </div>
            </div>

            <div class="form-group">
                <label>Birth Year:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="birth_year_str" class="form-control" placeholder="...Birth Year">
                </div>
            </div>
            <div class="form-group">
                <label>Country:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="country" class="form-control" placeholder="...Country">
                </div>
            </div>
            <div class="form-group">
                <label>Description:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="description" class="form-control" style= "height: 100px;" placeholder="...Description">
                </div>
            </div>
            <br />
            <button class="btn btn-default" type="submit"><i class="fa fa-search"></i> Submit</button>
            <button class="btn btn-default" type="reset"><i class="fa fa-refresh"></i> Reset</button>
        </form>
        <jsp:include page="/gallery/view/shared/Scripts.jsp" />
    </div>
    </body>
</html>
