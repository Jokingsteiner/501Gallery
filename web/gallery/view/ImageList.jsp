<%@ page import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
//	int pageTotal = (int)request.getAttribute("pageTotal");
//	int pageNum = (int)request.getAttribute("pageNum");
//	int pageSize = (int)request.getAttribute("pageSize");
	LinkedList<Image> result = (LinkedList<Image>) request.getAttribute("result");
//	String galleryName = (String) request.getAttribute("gallery_name");
	String cond = (String) request.getAttribute("sql_condition");
    String byGallery = (String) request.getAttribute("byGallery");
	Gallery gallery = new Gallery();
    if (byGallery.equals("true"))
        gallery = (Gallery) request.getAttribute("gallery");
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
								if (gallery != null) {
							%>
								<h3 class="panel-title"><%=gallery.getName()%> - <%=imgCount%> <%if(imgCount <= 1){out.print("image");}else{out.print("images");}%></h3>
								<div><%=gallery.getDescription()%></div>
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
	<%
		if (byGallery.equals("true")) {
	%>
	<div class="prod-panel col-lg-12" style="margin-top: 2%">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Modify Gallery Info</h3>
			</div>
			<div class="panel-body">
				<form action="gallery/UpdateControl" method="post">
					<input type="hidden" name="update_type" value="gallery" class="form-control">
					<input type="hidden" name="gallery_name" value="<%=gallery==null?null:gallery.getName()%>" class="form-control">
					<div class="form-group col-lg-12">
						<label>Gallery name:</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" name="name" class="form-control" placeholder="...Gallery Name">
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
	<%
		}
	%>
</div>
<jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Scripts.jsp" />
</body>
</html>
