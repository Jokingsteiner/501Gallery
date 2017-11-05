<%@ page language="java" import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
    Artist artist = (Artist)request.getAttribute("artist");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Artist  Detail</title>
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
                    <img id="photo" src="http://www.world-art.ru/img/people/90000/80224.jpg" onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';"><br>
                </div>
                <div class="info-panel row-items col-sm-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><%=artist.getName()%></h3>
                        </div>
                        <div class="panel-body">
                            <div class="col-sm-12 col-xs-12" role="group" aria-label="...">
                                <ul class="list-group col-lg-12 col-sm-12">
                                    <li class="list-group-item">ID: <%=artist.getID()%></li>
                                    <li class="list-group-item">Birth Year: <%=artist.getBirthYear()%></li>
                                    <li class="list-group-item">Country : <%=artist.getCountry()%></li>
                                    <li class="list-group-item">Description : <%=artist.getDescription()%></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="prod-panel col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Modify Information</h3>
                        </div>
                        <div class="panel-body">
                            <form action="gallery/UpdateControl" method="post">
                                <input type="hidden" name="update_type" value="artist" class="form-control">
                                <input type="hidden" name="artist_id" value="<%=artist.getID()%>" class="form-control">
                                <div class="form-group col-lg-12">
                                    <label>Artist Name:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input type="text" name="name" class="form-control" placeholder="...Artist Name">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Birth Year:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="birth_year" class="form-control" placeholder="...Birth Year">
                                    </div>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Country:</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                        <input type="text" name="country" class="form-control" placeholder="...Country">
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
