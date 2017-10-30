<%@ page import="java.util.*,domain.*,controller.*" pageEncoding="ISO-8859-1"%>
<%
//	int pageTotal = (int)request.getAttribute("pageTotal");
//	int pageNum = (int)request.getAttribute("pageNum");
//	int pageSize = (int)request.getAttribute("pageSize");
	LinkedList<Gallery> result = (LinkedList<Gallery>) request.getAttribute("result");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Gallery List</title>
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}/gallery/view/shared/Header.jsp" />
<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<div class="row">
				<div class="col-sm-12 col-md-6">
					<div id="parameters-form" class="form-inline" style="margin-left: 10px !important;">
						<div class="form-group">
							<button id="movie-id-sort" class="btn btn-default">
								Movie ID&nbsp;
								<i id="active-toggle-icon" class="glyphicon glyphicon-chevron-down"></i>
							</button>
						</div>
						<div class="form-group">
							<button id="movie-year-sort" class="btn btn-default">Year&nbsp;</button>
						</div>
						<div class="form-group">
							<button id="movie-title-sort" class="btn btn-default">Title&nbsp;</button>
						</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-6">
					<ul class="page-selector pagination pull-right" style="margin-right: 10px !important;"></ul>
				</div>
			</div>
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
										<%--TODO: change link to gallery view--%>
										<a href="gallery/GalleryControl" id = "poster" class="thumbnail">
											<%
												if ( gallery.getImages().size() != 0) {
											%>
												<img src="<%=gallery.getImages().getFirst()%>" alt="img_id:<%=gallery.getImages().getFirst()%>">
											<%
												}
												else {
											%>
												<img src="http://www.musicmart.com.au/media/catalog/category/thumbnail_40.jpg">
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
<jsp:include page="/gallery/view/shared/Scripts.jsp" />
</body>
</html>
