<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-content">
	<div class="close-modal" data-dismiss="modal">
		<div class="lr">
			<div class="rl"></div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="modal-body" value="">

					<h2>${contentDetail.contentName}</h2>

					<%-- <img  class="img-responsive img-centered" src= "/bunnyland/thumbnailImage.do?contentNumber=${contentDetail.contentNumber}" width="300px" height="300px" /> --%>
					<img  class="img-responsive img-centered" src= "/bunnyland-content/thumbnailImage.do?contentNumber=${contentDetail.contentNumber}" width="300px" height="300px" />
					<c:forEach var="filedetail" items="${fileDetail}">
						<!-- 만일 다운로드를 원한다면 아래의 주석처리된 부분을 사용하면 된다. -->
						<%-- <a href="filedownload.do?fileName=${filedetail.fileName }"> ${filedetail.fileName}</a> --%>
						<p> ${filedetail.fileName}</p>
						<p class="item-intro text-muted">${filedetail.fileType} / ${filedetail.fileSize}</p>
						<br>
					</c:forEach>
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">
						<i class="fa fa-times"></i> 수정
					</button> -->
					<form action="deleteContent.do">
						<input type="hidden" name="contentNum" value="${contentDetail.contentNumber}">
						<!-- <input type="submit" class="btn btn-block">
									<iclass="fa fa-times"></i>삭제 -->
						<button type="submit" class="btn btn-primary">
						<i class="fa fa-times"></i> 삭제
					</button>
					
					
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						<i class="fa fa-times"></i> CLOSE
					</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>