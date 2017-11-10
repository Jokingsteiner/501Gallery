<%@ page language="java" import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
    Detail detail = (Detail)request.getAttribute("detail");
	Image img = (Image)request.getAttribute("image");
    Artist artist = (Artist)request.getAttribute("artist");
    Gallery gallery = Gallery.getGalleryByID(img.getGallery());
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Image Detail</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/gallery/sources/css/poster_list.css">
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
            <div class="row">
                <div class="row-items col-sm-6">
                    <a href="<%=img.getLink()%>"><img id="photo" src="<%=img.getLink()%>" onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';"></a><br>
                </div>

                <div class="info-panel row-items col-sm-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><%=img.getTitle()%></h3>
                        </div>
                        <div class="panel-body">
                            <div class="col-sm-12 col-xs-12" role="group" aria-label="...">
                                <ul class="list-group col-lg-12 col-sm-12">
                                    <li class="list-group-item">ID: <%=detail.getImgID()%></li>
                                    <li class="list-group-item">Gallery: <a href="gallery/SearchControl?forImage=true&gallery_id=<%=gallery.getID()%>"><%=gallery.getName()%></a></li>
                                    <li class="list-group-item">Artist: <a href="gallery/DetailControl?detail_type=artist&artist_id=<%=img.getArtist()%>"><%=artist.getName()%></a></li>
                                    <li class="list-group-item">Year: <%=detail.getYear()%></li>
                                    <li class="list-group-item">Type : <%=detail.getType()%></li>
                                    <li class="list-group-item">Size : <%=detail.getWidth()%> x <%=detail.getHeight()%></li>
                                    <li class="list-group-item">Location : <%=detail.getLocation()%></li>
                                    <li class="list-group-item">Description : <%=detail.getDescription()%></li>
                                    <li class="list-group-item">URL : <a href="<%=img.getLink()%>">Click Me!</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="prod-panel col-lg-12" style="margin-top: 2%">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Modify Information</h3>
                        </div>
                        <div class="panel-body">
                            <form action="gallery/UpdateControl" method="post">
                                <input type="hidden" name="update_type" value="image" class="form-control">
                                <input type="hidden" name="image_id" value="<%=img.getID()%>" class="form-control">
                                <div class="form-group col-lg-6">
                                    <label>Image Title:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input type="text" name="title" class="form-control" placeholder="...Image Title">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>URL:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="link" class="form-control" placeholder="...URL">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Gallery</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <select  name="gallery_id" class="form-control">
                                            <option value="">Select Gallery...</option>
                                            <%
                                                LinkedList<Gallery> galleryList = Gallery.fetchGalleries();
                                                for (Gallery galleryOption: galleryList) {
                                            %>
                                            <option value="<%=galleryOption.getID()%>"><%=galleryOption.getName()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Artist</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <select  name="artist_id" class="form-control">
                                            <option value="">Select Artist...</option>
                                            <%
                                                LinkedList<Artist> artistList = Artist.fetchArtist(null);
                                                for (Artist artistOption: artistList) {
                                            %>
                                            <option value="<%=artistOption.getID()%>"><%=artistOption.getName()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Type:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="type" class="form-control" placeholder="...Image Type">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Year:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="year" class="form-control" placeholder="...Created Year">
                                    </div>
                                </div>

                                <div class="form-group col-lg-12">
                                    <label>Location:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="location" class="form-control" placeholder="...Location">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Width:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="width" class="form-control" placeholder="...Image Width">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Height:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="height" class="form-control" placeholder="...Image Height">
                                    </div>
                                </div>

                                <div class="form-group col-lg-12">
                                    <label>Description:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="description" class="form-control" style= "height: 100px;" placeholder="...Description">
                                    </div>
                                </div>
                                <br />
                                <div  style="padding-left:1.5%">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i> Submit</button>
                                    <button class="btn btn-default" type="reset"><i class="fa fa-refresh"></i> Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Scripts.jsp" />
</html>
