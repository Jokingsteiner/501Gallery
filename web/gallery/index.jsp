<%@ page import="java.util.*,domain.*,controller.*" pageEncoding="ISO-8859-1"%>
<%
    LinkedList<Gallery> result = Gallery.fetchGalleries();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Gallery List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/gallery/sources/css/poster_list.css">
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Header.jsp" />
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="prod-panel col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Galleries</h3>
                        </div>
                        <div class="panel-body">
                            <%
                                if (result != null) {
                            %>
                            <%
                                int colCount = 0;
                                for(Gallery gallery:result){
                                    if (colCount == 0) {
                            %>
                            <div class="row">
                                <%
                                    }
                                %>
                                <div class="col-xs-6 col-md-2">
                                    <a href="gallery/SearchControl?forImage=true&gallery_id=<%=gallery.getID()%>" id = "poster" class="thumbnail">
                                        <%
                                            if ( gallery.getImages().size() != 0) {
                                        %>
                                        <img src="<%=Image.getImageById(gallery.getImages().getFirst()).getLink()%>" onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';">
                                        <%
                                        }
                                        else {
                                        %>
                                        <img src="gallery/image/thumbnail_unavailable.jpg">
                                        <%
                                            }
                                        %>
                                        <div><%=gallery.getName()%></div>
                                    </a>
                                </div>

                                <%
                                    if (colCount == 5) {
                                %>
                            </div>
                            <%
                                    }
                                    colCount = (colCount + 1) % 6;
                                }
                            %>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Scripts.jsp" />
</body>
</html>
