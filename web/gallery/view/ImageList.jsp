<%@ page import="java.util.*,domain.*,controller.*" pageEncoding="ISO-8859-1"%>
<%
//	int pageTotal = (int)request.getAttribute("pageTotal");
//	int pageNum = (int)request.getAttribute("pageNum");
//	int pageSize = (int)request.getAttribute("pageSize");
	LinkedList<Image> result = (LinkedList<Image>) request.getAttribute("result");
	String galleryName = (String) request.getAttribute("gallery_name");
	int imgCount = (Integer) request.getAttribute("image_count");
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
							<h3 class="panel-title"><%=galleryName%> - <%=imgCount%> <%if(imgCount <= 1){out.print("image");}else{out.print("images");}%> </h3>
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
                                            <img src="<%=img.getLink()%>" alt="img_id:<%=img.getID()%>">
										</a>
                                        <a href="DetailControl?link='<%=img.getLink()%>'&image_id=<%=img.getID()%>">More...</a>
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
