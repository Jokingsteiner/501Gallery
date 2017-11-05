<%@ page import="java.util.*,domain.*" pageEncoding="ISO-8859-1"%>
<%
//	int pageTotal = (int)request.getAttribute("pageTotal");
//	int pageNum = (int)request.getAttribute("pageNum");
//	int pageSize = (int)request.getAttribute("pageSize");
	LinkedList<Artist> result = (LinkedList<Artist>) request.getAttribute("result");
	int artistCount = (Integer) request.getAttribute("artist_count");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Artist Search Result</title>
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
							<h3 class="panel-title"><%=artistCount%> <%if(artistCount <= 1){out.print("artist");}else{out.print("artists");}%> found</h3>
						</div>
						<div class="panel-body">
							<%
								if (result != null) {
							%>
								<%
									int colCount = 0;
									for(Artist artist: result){
										if (colCount == 0) {
								%>
								<div class="row">
									<%
										}
									%>
									<div class="col-xs-6 col-md-2">
										<a href="gallery/DetailControl?detail_type=artist&artist_id=<%=artist.getID()%>" id = "poster" class="thumbnail">
                                            <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/1200px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg"  onError="this.onerror=null;this.src='gallery/image/thumbnail_unavailable.jpg';">
											<div id="Title"><%=artist.getName()%></div>
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
								else {out.print("NO HYML OUTPUT");}
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
