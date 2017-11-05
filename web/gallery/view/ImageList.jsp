<%@ page import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
//	int pageTotal = (int)request.getAttribute("pageTotal");
//	int pageNum = (int)request.getAttribute("pageNum");
//	int pageSize = (int)request.getAttribute("pageSize");
	LinkedList<Image> result = (LinkedList<Image>) request.getAttribute("result");
	String galleryName = (String) request.getAttribute("gallery_name");
	String cond = (String) request.getAttribute("sql_condition");
    String byGallery = (String) request.getAttribute("byGallery");
	int imgCount = result==null?0:result.size();
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
	<div class="panel panel-primary">
		<div class="panel-heading">
			<div class="row">
				<div class="prod-panel col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<%
								if (galleryName != null) {
							%>
								<h3 class="panel-title"><%=galleryName%> - <%=imgCount%> <%if(imgCount <= 1){out.print("image");}else{out.print("images");}%></h3>
							<%
								}
								else {
							%>
								<h3 class="panel-title"><%=imgCount%> <%if(imgCount <= 1){out.print("image");}else{out.print("images");}%> found</h3>
							<%
								}
							%>

						</div>
						<div class="panel-body">
							<%
								if (result != null) {
							%>
								<%
									int colCount = 0;
									for(Image img: result){
										if (colCount == 0) {
								%>
								<div class="row">
									<%
										}
									%>
									<div class="col-xs-6 col-md-2">
                                        <div id="Title"><%=img.getTitle()%></div>
										<a href="<%=img.getLink()%>" id = "poster" class="thumbnail">
                                            <img src="<%=img.getLink()%>"  onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';">
										</a>
										<form action="gallery/DetailControl?detail_type=image&image_id=<%=img.getID()%>" method="post">
											<button class="btn btn-default col-lg-6" type="submit"><i class="fa fa-search"></i>More...</button>
										</form>
                                        <form action="gallery/DeleteControl" method="post">
                                            <input type="hidden" name="image_id" value="<%=Integer.toString(img.getID())%>" class="form-control">
                                            <input type="hidden" name="cond" value="<%=cond%>" class="form-control">
                                            <input type="hidden" name="byGallery" value="<%=byGallery%>" class="form-control">
                                            <button class="btn btn-default col-lg-6" type="submit"><i class="fa fa-search"></i><strong>DELETE</strong></button>
                                        </form>
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
