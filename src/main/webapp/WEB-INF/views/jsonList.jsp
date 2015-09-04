
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%-- <c:forEach items="${content_jsonList}" var="list">
			content1: [${list}]
		</c:forEach>

		<c:forEach items="${file_jsonList}" var="list">
			${list}
		</c:forEach>
 --%>
[
<c:forEach items="${content_jsonList}" var="content">
{"files" :
	<c:forEach items="${file_jsonList}" var="file">
			 [{"id" : ${file.fileNumber}, "filesize" : ${file.fileSize}, "fileName" : ${file.fileName }, "filepath" : ${file.filePath },
			 "filetype" : ${file.fileType }, "fileUrl" :  bunnyland.iptime.org:9001/bunnyland-content/filedownload.do?fileName=${file.fileName}, "contentNumber" : ${file.content_contentNumber }}],	
	</c:forEach>
	"id" : ${content.contentNumber }, "title" : ${contet.contentName }, "added" : ${content.registDate },
	"thumbnail" : bunnyland.iptime.org:9001/bunnyland-content/thumbnailImage.do?contentNumber=${content.contentNumber}, "desc" : ${content.description }}  
</c:forEach>]<br>


<!-- [{"files": [{"id": 185, "added": "2015-05-04 02:45:22", "filesize":
2054762756, "title": "\ubf40\ub85c\ub85c\uc138\uacc4\uc9c0\ub3c4.tda"}],
"added": "2015-05-04 02:45:22", "title":
"\ubf40\ub85c\ub85c\uc138\uacc4\uc9c0\ub3c4", "id": 137, "thumbnail":
"\ubf40\ub85c\ub85c\uc138\uacc4\uc9c0\ub3c4/\ubf40\ub85c\ub85c\uc138\uacc4\uc9c0\ub3c4.jpg",
"desc": null}, -->
