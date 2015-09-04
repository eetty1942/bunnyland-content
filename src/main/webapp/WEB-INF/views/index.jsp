<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ page import="net.bunnyland.dao.ContentDao"
	import="net.bunnyland.dao.ContentFileDao"
	import="net.bunnyland.dto.ContentDto"
	import="net.bunnyland.dto.ContentFileDto"
	import="org.springframework.ui.Model" import="java.util.List"%>
<!DOCTYPE html>
<html lang="kr">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- 페이지의 타이틀 제목(브라우저 탭에 들어가는 제목) -->
<title>BunnyLand Content File Server - Manager Page</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='https://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- IE 구버전에서 html5를 지원해주기 위한 설정 옵션입니다. -->
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- 컨텐츠 상세 내역을 보기위한 모달 파싱 -->
<script type="text/javascript">
	function openContent(idx) {

		$.ajax({
		     type     : "get",
		     url      : 'contentdetail.do',	 // 게시글 내용물 url
		     data     : {idx:idx}, // parameter
		     success  : function(data) {

		    	 $("#portfolioModal1").html(data);
		     }
		 }); 
	}
</script>

</head>

<body id="page-top" class="index">

	<!-- Navigation -->
	<!-- 상단의 네비게이션 메뉴 구조 부분입니다. -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">TECHNONIA</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#portfolio">Content List</a></li>
					<li><a class="page-scroll" href="#services">Content	Register</a></li>
					<li><a class="page-scroll" href="#about">Push list</a></li>
					<li><a class="page-scroll" href="#team">Push</a></li>
					<li><a class="button" href="logout.do">LOGOUT</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->
	<!-- 메인에 들어가는 문구 영역 -->
	<header>
		<div class="container">
			<div class="intro-text">
				<div class="intro-lead-in">TECHNONIA</div>
				<div class="intro-heading">BunnyLand Contents</div>
				<div class="intro-lead-in">${managerInfo.name} / ${managerInfo.jobRank }</div>
			</div>
		</div>
	</header>

	<!-- Portfolio Grid Section -->
	<!-- 컨텐츠 리스트를 보는 영역 -->
	
	<section id="portfolio" class="bg-light-gray">
	
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Content List</h2>
					<!-- <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>   -->
				</div>
			</div>
				<div"row">
					<c:forEach items="${contentList}" var="content">
				
					<div class="col-md-4 col-sm-6 portfolio-item">
						<a href="#portfolioModal1" onclick="openContent('${content.contentNumber}')" class="portfolio-link" data-toggle="modal">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fa fa-th-list fa-3x"></i>
								</div>
							</div>
							<%-- <img  src= "/bunnyland/thumbnailImage.do?contentNumber=${content.contentNumber}" width="350" height="260.094" /> --%>
							<img  src= "/bunnyland-content/thumbnailImage.do?contentNumber=${content.contentNumber}" width="350" height="260.094" /> 
							
						</a>
						<div class="portfolio-caption">
							<h4>${content.contentName}</h4>
							<%-- ${content.contentNumber} --%>
							<p class="text-muted">${content.description}</p>
						</div>
					</div>
					</c:forEach>
				</div>
		</div>
	</section>

	<!-- Services Section -->
	<!-- 컨텐츠 등록 영역 -->
	<section id="services">
		<div class="container">
			<div class="row">
			
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Content Register</h2>
				</div>
			</div>
			<div class="row text-center" style="style ="position:absolute; top:30px; left:0; right:0; text-align:center"">
				<div class="col-md-4">
					<!-- <div align=center> -->
						<a href="#RegisterModal" class="RegisterModal-link" data-toggle="modal">
						<span class="fa-stack fa-4x"> 
							<i class="fa fa-circle fa-stack-2x text-primary"></i>
							<i class="fa fa-plus fa-stack-1x fa-inverse"></i>
						</span>
							<h4 class="service-heading">컨텐츠 등록</h4>
						</a>
					<!-- </div> -->
				</div>
			</div>
		</div>
	</section>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
	<!--Push list Section -->
	<!-- 푸시 목록 영역 -->
	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Push List</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<ul class="timeline">
						<li>
							<div class="timeline-image">
								<a href="#PushlistModal1" class="PushlistModal1-link" data-toggle="modal">
									<img class="img-circle img-responsive"	src="img/about/content.jpg" alt="">
								</a>
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>콘텐츠 Push</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">Content 내용</p>
								</div>
							</div>
						</li>
						<li>
							<div class="timeline-inverted">
								<a href="#PushlistModal2" class="PushlistModal2-link"
									data-toggle="modal">
									<div class="timeline-image">
										<img class="img-circle img-responsive" src="img/about/event.jpg" alt="">
								</a>
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>이벤트 Push</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">Event 내용</p>
								</div>
							</div>
						</li>
						<li>
							<div class="timeline-image">
								<a href="#PushlistModal3" class="PushlistModal3-link" data-toggle="modal"> 
									<img class="img-circle img-responsive"	src="img/about/promotion.png" alt="">
								</a>
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>프로모션 Push</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">Promotion 내용</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<h4>
									Happy <br> BunnyLand <br> Push List
								</h4>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<!-- Team Section -->
	<!-- 푸시 등록 영역 -->
	<section id="team" class="bg-light-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Push</h2>
					<!-- <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>  -->
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<a href="#PushModal1" class="PushModal1-link" data-toggle="modal">
						<div class="team-member">
							<img src="img/about/content.jpg" class="img-responsive img-circle" alt="">
							<h4>신규 콘텐츠 push</h4>
						</div>
					</a>
				</div>
				<div class="col-sm-4">
					<a href="#PushModal2" class="PushModal2-link" data-toggle="modal">
						<div class="team-member">
							<img src="img/about/event.jpg" class="img-responsive img-circle" alt="">
							<h4>신규 이벤트 push</h4>
						</div>
					</a>
				</div>
				<div class="col-sm-4">
					<a href="#PushModal2" class="PushModal2-link" data-toggle="modal">
						<div class="team-member">
							<img src="img/about/promotion.png" class="img-responsive img-circle" alt="">
							<h4>신규 포로모션 push</h4>
						</div>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<!--  <p class="large text-muted"> 필요한내용</p>   -->
				</div>
			</div>
		</div>
	</section>

	<!-- footer 영역 = 회사 정보 연락처 등을 기입한다. -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">@Copyright 2015 TECHNONIA. All Rights Reserved.</span>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<ul class="list-inline quicklinks">
						<li><a href="http://www.technonia.com/kr/00.company/company_01.html">회사소개</a></li>
						<li><a href="http://www.technonia.com/kr/00.company/company_05.html">오시는 길</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<!--//////////////////////////////////////////////////////모달영역//////////////////////////////////////////////////////////////////  -->
	<!-- Portfolio Modals -->
	<!-- Use the modals below to showcase details about your portfolio projects! -->

	<!-- Portfolio Modal 1 -->
	<!-- 컨텐츠 상세 모달 -->
	<div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
	</div>


	<!--Register Modal  -->
	<!-- 컨텐츠 등록 폼 모달 -->
	<div class="Register-modal modal fade" id="RegisterModal" tabindex="-1"	role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<form action="<c:url value ='registContent.do'/>" method="POST" enctype="multipart/form-data">
							<div class="modal-body" align="center">
								<h2>컨텐츠 등록</h2>
								<table>
									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">제 목</td>
										<td><input name="title" size="50" maxlength="100" required></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">썸네일</td>
 										<td><input type="file" name="thumb" size="35" maxlength="100"></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">컨텐츠</td>
 										<td><input type="file" name="f" size="35" maxlength="100" multiple></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="1" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">내용</td>
										<td><textarea name="description" cols="50" rows="13"></textarea></td>
										<td>&nbsp;</td>
									</tr>

									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<input type="hidden" name="manager" value= "${managerInfo.managerNumber}">

								</table>
 								 <button type="submit" class="btn btn-primary" >
								 	<i class="fa fa-times"></i>등 록 
								</button>
								<button type="button" class="btn btn-primary" data-dismiss="modal">
									<i class="fa fa-times"></i> 취 소
								</button>
							</div>
						</form>
 					</div>
				</div>
			</div>
		</div>
	</div>

	<!--PushList1 Modal  -->
	<!-- 신규 푸시 -->
	<div class="Pushlist1-modal modal fade" id="PushlistModal1" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body" align=center>
							<!-- Project Details Go Here -->
							<h2>CONTENT PUSH LIST</h2>
							<section class="content">
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr height="5">
										<td width="5"></td>
									</tr>

									<td width="5"></td>
									<!-- <td width="400">제목</td> -->
									<td width="600" align= "center">내 용</td>
									<td width="100" align= "center">작성자</td>
									<td width="7"></td>

									<tr height="25" align="center">
									</tr>
									<tr height="1" bgcolor="#D2D2D2">
										<td colspan="6"></td>
									</tr>

									<tr height="1" bgcolor="#82B5DF">
										<td colspan="6" width="752"></td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td colspan="4" height="5"></td>
									</tr>
									<c:forEach items="${newList}" var= "new">
										<tr>
											<td width="450" align= "center">${new.pushDescription }</td>
											<td width="100" align= "center">${new.managerInfo_managerNumber}</td>
										</tr>
									</c:forEach>

								</table>
							</section>

							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<i class="fa fa-times"></i> 취 소
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--PushList2 Modal  -->
	<!-- 이벤트 프로모션 푸시 -->
	<div class="Pushlist2-modal modal fade" id="PushlistModal2" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body" align=center>
							<!-- Project Details Go Here -->
							<h2>EVENT PUSH LIST</h2>
							<section class="content">
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr height="5">
										<td width="5"></td>
									</tr>

									<td width="5"></td>
									<!-- <td width="400">제목</td> -->
									<td width="150" align= "center">URL</td>
									<td width="450" align= "center">내 용</td>
									<td width="100" align= "center">작성자</td>
									<td width="7"></td>

									<tr height="25" align="center">
									</tr>
									<tr height="1" bgcolor="#D2D2D2">
										<td colspan="6"></td>
									</tr>

									<tr height="1" bgcolor="#82B5DF">
										<td colspan="6" width="752"></td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td colspan="4" height="5"></td>
									</tr>
									<c:forEach items="${eventList}" var= "event">
										<tr>
											<td width="150" align= "center">${event.url}</td>
											<td width="450" align= "center">${event.pushDescription }</td>
											<td width="100" align= "center">${event.managerInfo_managerNumber}</td>
										</tr>
									</c:forEach>

								</table>
							</section>

							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<i class="fa fa-times"></i> 닫 기
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--PushList3 Modal  -->
	<div class="Pushlist3-modal modal fade" id="PushlistModal3"	tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body" align=center>
							<!-- Project Details Go Here -->
							<h2>PROMOTION PUSH LIST</h2>
							<section class="content">
								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr height="5">
										<td width="5"></td>
									</tr>

									<td width="5"></td>
									<!-- <td width="400">제목</td> -->
									<td width="400">URL</td>
									<td width="400">내 용</td>
									<td width="100">작성자</td>
									<td width="7"></td>

									<tr height="25" align="center">
									</tr>
									<tr height="1" bgcolor="#D2D2D2">
										<td colspan="6"></td>
									</tr>

									<tr height="1" bgcolor="#82B5DF">
										<td colspan="6" width="752"></td>
									</tr>
								</table>

								<table width="100%" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td colspan="4" height="5"></td>
									</tr>
									<c:forEach items="${promotionList}" var= "promotion">
										<tr>
											<td width="150" align= "center">${promotion.url}</td>
											<td width="450" align= "center">${promotion.pushDescription }</td>
											<td width="100" align= "center">${promotion.managerInfo_managerNumber}</td>
										</tr>
									</c:forEach>
								</table>
							</section>

							<button type="button" class="btn btn-primary" data-dismiss="modal">
								<i class="fa fa-times"></i> 닫 기
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Push1 Modal  -->
	<div class="Push1-modal modal fade" id="PushModal1" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body" align=center>
							<!-- Project Details Go Here -->
							<h2>PUSH 등록</h2>
							<form action="pushNew.do" method="post">
							<input type="hidden" name="managerInfo_managerNumber" value = "${new.managerInfo_managerNumber}">
							<table>
								<tr>
									<td>&nbsp;</td>
									<td align="center">내용</td>
									<td><textarea name="pushDescription" cols="50" rows="13"></textarea></td>
									<td>&nbsp;</td>
								</tr>

								<tr height="10" bgcolor="#ffffff">
									<td colspan="4"></td>
								</tr>
							</table>
							
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-times"></i>등 록
							</button>

							<button type="button" class="btn btn-primary" data-dismiss="modal">
								<i class="fa fa-times"></i> 취 소
							</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Push2 Modal  -->
	<div class="Push2-modal modal fade" id="PushModal2" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-content">
			<div class="close-modal" data-dismiss="modal">
				<div class="lr">
					<div class="rl"></div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<div class="modal-body" align=center>
							<!-- Project Details Go Here -->
							<h2>PUSH 등록</h2>
							<form action="pushEvent.do" method="post">
							<input type="hidden" name="managerInfo_managerNumber" value = "${new.managerInfo_managerNumber}">
								<table>
									<div class="radio-inline">
										<label>
											<input type="radio" name="pushKind"	id="inlineoptionsRadios1" value="이벤트" checked> 이벤트
										</label>
									</div>
									<div class="radio-inline">
										<label> 
											<input type="radio" name="pushKind"	id="inlineoptionsRadios2" value="프로모션" checked> 프로모션
										</label>
									</div>

									<!-- <tr>
									<td>&nbsp;</td>
									<td align="center">제 목</td>
									<td><input name="title" size="50" maxlength="100"></td>
									<td>&nbsp;</td>
								</tr>
								<tr height="10" bgcolor="#ffffff">
									<td colspan="4"></td>
								</tr> -->
									<tr>
										<td>&nbsp;</td>
										<td align="center">U R L</td>
										<td><input name="url" size="50" maxlength="100"></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center">내용</td>
										<td><textarea name="pushDescription" cols="50" rows="13"></textarea></td>
										<td>&nbsp;</td>
									</tr>

									<tr height="10" bgcolor="#ffffff">
										<td colspan="4"></td>
									</tr>

								</table>
							
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-times"></i> 등 록
							</button>

							<button type="button" class="btn btn-primary" data-dismiss="modal">
								<i class="fa fa-times"></i> 취 소
							</button>
							</form>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<!-- <script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script> -->

	<!-- Custom Theme JavaScript -->
	<script src="js/agency.js"></script>

</body>

</html>
