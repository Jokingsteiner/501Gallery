
<%@ page import="domain.*, java.util.LinkedList" contentType="text/html;charset=UTF-8" language="java" %>

<%
    LinkedList<Gallery> galleryList = Gallery.fetchGalleries();
    LinkedList<Artist> artistList = Artist.fetchArtist(null);
%>

<html>
    <head>
        <title>New Image</title>
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

        <h1 align="center" class="my-title">Add Image</h1>
        <form action="gallery/AddImageControl" method="post">
            <div class="form-group">
                <label>Image Title:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" name="title" class="form-control" placeholder="...Image Title">
                </div>
            </div>

            <div class="form-group col-lg-6" style="padding-left:0;">
                <label>Gallery</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <select  name="gallery_id" class="form-control">
                        <option value="">Select Gallery...</option>
                        <%
                            for (Gallery gallery: galleryList) {
                        %>
                                <option value="<%=gallery.getID()%>"><%=gallery.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group col-lg-6" style="padding-right:0;">
                <label>Artist</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <select  name="artist_id" class="form-control">
                        <option value="">Select Artist...</option>
                        <%
                            for (Artist artist: artistList) {
                        %>
                                <option value="<%=artist.getID()%>"><%=artist.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group col-lg-6" style="padding-left:0;">
                <label>Type:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="type" class="form-control" placeholder="...Type">
                </div>
            </div>

            <div class="form-group col-lg-6" style="padding-right:0;">
                <label>Created Year:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="year" class="form-control" placeholder="...Created Year">
                </div>
            </div>

            <div class="form-group col-lg-3" style="padding-left:0;">
                <label>Width:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="width" class="form-control" placeholder="...Width">
                </div>
            </div>

            <div class="form-group col-lg-3" style="padding-left:0;">
                <label>Height:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users"></i></span>
                    <input type="text" name="height" class="form-control" placeholder="...Height">
                </div>
            </div>

            <div class="form-group col-lg-6" style="padding-right:0;">
                <label>Created Location:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" name="location" class="form-control" placeholder="...Location">
                </div>
            </div>

            <div class="form-group">
                <label>Link(URL):</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" name="link" class="form-control" placeholder="...URL">
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
        <jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Scripts.jsp" />
    </div>
    </body>
</html>
