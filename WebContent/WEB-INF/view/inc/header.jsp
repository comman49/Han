<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctxName" value="${pageContext.request.contextPath}"/>

		<div id="header">
			<div class="top-wrapper">
				<h1 id="logo"><a href="${ctxName}/index.jsp">
				<img src="${ctxName}/resource/images/logo.png" alt="뉴렉처" /></a></h1>
				<h2 class="hidden">메인메뉴</h2>
				<ul id="mainmenu" class="block_hlist">
					<li>
						<a href="">학습가이드</a>
					</li>
					<li>
						<a href="" >과정선택</a>
					</li>
					<li>
						<a href="" >인기과정</a>
					</li>
				</ul>
				<form id="searchform" action="" method="get">
					<fieldset>
						<legend class="hidden">
							과정검색폼
						</legend>
						<label for="query">과정검색</label>
						<input type="text" name="query" />
						<input type="submit" class="button" value="검색" />
					</fieldset>
				</form>
				<h3 class="hidden">로그인메뉴</h3>
				<ul id="loginmenu" class="block_hlist">
					<li>
						<a href="../index.jsp">HOME</a>
					</li>
					<li>
						<!-- 로그인 글자 앞에 아이디 출력하는 방법 -->
						<!-- pageContext에서 request로 아이디를 가져온다. -->
						<!-- userPrincipal에 user 정보를 저장한다. -->
						<!-- empty는 대상이 null이거나 빈 문자열인지를 확인한다. -->
						<%-- <c:if test="${empty pageContext.request.userPrincipal}">
							<a href="../joinus/login">로그인</a>
						</c:if>
						<c:if test="${not empty pageContext.request.userPrincipal}">
							<a href="${ctxName}/j_spring_security_logout">${pageContext.request.userPrincipal.name} 로그아웃</a>
						</c:if> --%>
						
						
							<a href="../joinus/login">로그인</a>
							<!-- 스프링에서 제공하는 방법으로 로그아웃 글자 앞에 아이디를 출력하는 방법 -->
							<!-- property 안의 단어들은 예약어이므로 틀리지 않도록 주의 -->
							<!-- j_spring_security_logout -> 스프링에서 제공하는 로그아웃 기능 -->
							<a href="${ctxName}/j_spring_security_logout"><security:authentication property="name" />로그아웃</a>
							
							
							<!-- 회원의 등급을 가져오는 방법 -->
							<security:authentication property="authorities" var="authorities"/>
							
							
							<!-- 왜 여기서 forEach를 사용하는가? -->
							<!-- 권한이 여러개니까!!-->
							<c:forEach items="${authorities}" var = "auth">
								${auth.authority}
							</c:forEach>
					</li>
					<li>
						<a href="../joinus/join.jsp">회원가입</a>
					</li>
				</ul>
				<h3 class="hidden">회원메뉴</h3>
				<ul id="membermenu" class="clear">
					<li>
						<a href=""><img src="../images/menuMyPage.png" alt="마이페이지" /></a>
					</li>
					<li>
						<a href="notice.jsp"><img src="../images/menuCustomer.png" alt="고객센터" /></a>
					</li>
				</ul>
			</div>
		</div>