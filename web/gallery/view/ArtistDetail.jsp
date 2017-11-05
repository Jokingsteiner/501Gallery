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
                                <ul class="list-group col-lg-6 col-sm-12">
                                    <li class="list-group-item">ID: <%=artist.getID()%></li>
                                    <li class="list-group-item">Birth Year: <%=artist.getBirthYear()%></li>
                                    <li class="list-group-item">Country : <%=artist.getCountry()%></li>
                                    <li class="list-group-item">Description : <%=artist.getDescription()%></li>
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
