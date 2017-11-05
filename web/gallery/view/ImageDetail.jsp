<%@ page language="java" import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
    Detail detail = (Detail)request.getAttribute("detail");
	Image img = (Image)request.getAttribute("image");
    Artist artist = (Artist)request.getAttribute("artist");
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
            <div class="row">
                <div class="row-items col-sm-6">
                    <img id="photo" src="<%=img.getLink()%>" onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';"><br>
                </div>

                <div class="info-panel row-items col-sm-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><%=img.getTitle()%></h3>
                        </div>
                        <div class="panel-body">
                            <div class="col-sm-12 col-xs-12" role="group" aria-label="...">
                                <ul class="list-group col-lg-6 col-sm-12">
                                    <li class="list-group-item">ID: <%=detail.getImgID()%></li>
                                    <li class="list-group-item">Artist: <a href="gallery/DetailControl?detail_type=artist&artist_id=<%=img.getArtist()%>"><%=artist.getName()%></a></li>
                                    <li class="list-group-item">Year: <%=detail.getYear()%></li>
                                    <li class="list-group-item">Type : <%=detail.getType()%></li>
                                    <li class="list-group-item">Size : <%=detail.getWidth()%> x <%=detail.getHeight()%></li>
                                    <li class="list-group-item">Location : <%=detail.getLocation()%></li>
                                    <li class="list-group-item">Description : <%=detail.getDescription()%></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Scripts.jsp" />
</html>
