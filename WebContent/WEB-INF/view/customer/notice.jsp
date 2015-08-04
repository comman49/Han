<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
				<div id="content">
					<h2>공지사항</h2>
					<h3 class="hidden">방문페이지 로그</h3>
					<ul id="breadscrumb" class="block_hlist clear">
						<li>HOME</li>
						<li>
							고객센터
						</li>
						<li>
							공지사항목록
						</li>
					</ul>
					<h3 class="hidden">공지사항 목록</h3>
					<form id="content-searchform" class="article-search-form" action="notice.jsp" method="get">
						<fieldset>
							<legend class="hidden">
								목록 검색 폼
							</legend>
							<input type="hidden" name="pg" value="" />
							<label for="f"
							class="hidden">검색필드</label>
							<select name="f">
								<option value="TITLE">제목</option>
								<option value="CONTENT">내용</option>
							</select>
							<label class="hidden" for="q">검색어</label>
							<input type="text"
							name="q" value="" />
							<input type="submit" value="검색" />
						</fieldset>
					</form>
					<table class="article-list margin-small">
						<caption class="hidden">
							공지사항
						</caption>
						<thead>
							<tr>
								<th class="seq">번호</th>
								<th class="title">제목</th>
								<th class="writer">작성자</th>
								<th class="regdate">작성일</th>
								<th class="hit">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="n" items="${list}" >
							<tr>
								<td class="seq">${n.code}</td>
								<td class="title">
									<a href="noticeDetail?c=${n.code}">${n.title}</a>
									[${n.cmtCount}]
									<c:forEach var="f" items="${n.files}">
										${f.name},
									</c:forEach>					
								</td>
								<td class="writer">${n.writer}</td>
								<td class="regdate"><fmt:formatDate value="${n.regDate}" pattern="yyyy-MM-dd" /> </td>
								<td class="hit">${n.hit}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<p class="article-comment margin-small">


					
						<!-- 사용자의 권한이 admin일 경우만 글쓰기가 가능하도록 할 수 있다.-->
						<!-- 아래와 같이 설정하면 조건을 충족한 경우에만 태그 내부가 작동 -->
						<!-- ifAnyGranted : 나열한 역할 중 하나만 만족하면 가능 -->
						<!-- ifAllGranted : 나열한 역할 모두 만족하면 가능 -->
						<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_TEACHER">
							<a class="btn-write button" href="noticeReg">글쓰기</a>
						</security:authorize>
					</p>
					<p id="cur-page" class="margin-small">
						<span class="strong">1</span> /
						10	page
					</p>
					<div id="pager-wrapper" class="margin-small">
						<div class="pager clear">
							<p id="btnPrev">
								<a class="button btn-prev"
								href="notice.jsp">이전</a>
							</p>
							<ul>
								<li>
									<a class="strong" href="">1</a>
								</li>
								<li>
									<a href="">2</a>
								</li>
								<li>
									<a href="">3</a>
								</li>
								<li>
									<a href="">4</a>
								</li>
								<li>
									<a href="">5</a>
								</li>
							</ul>
							<p id="btnNext">
								<span class="button btn-next">다음</span>
							</p>
						</div>
					</div>
				</div>