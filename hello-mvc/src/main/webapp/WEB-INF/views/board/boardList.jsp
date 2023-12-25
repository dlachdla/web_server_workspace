<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-15
  Time: 오후 3:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            게시판
        </h1>
    </div>
    <c:if test="${loginMember != null}">
        <div class="flex justify-end">
            <button
                    type="button"
                    onclick="location.href = '${pageContext.request.contextPath}/board/boardCreate';"
                    class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글쓰기</button>
        </div>
    </c:if>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">번호</th>
                <th scope="col" class="px-6 py-3">제목</th>
                <th scope="col" class="px-6 py-3">작성자</th>
                <th scope="col" class="px-6 py-3">작성일</th>
                <th scope="col" class="px-6 py-3">첨부파일</th>
                <th scope="col" class="px-6 py-3">조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${boards}" var="board" varStatus="vs">
            <tr class="odd:bg-white even:bg-gray-50 border-b ">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${board.id}</th>

            <%-- 댓글 개수 배지 10 ge(>=)같거나크면 레드, 10 gt(>)이상 0 lt(<)이하 그레이 --%>
                <td class="px-6 py-4">
                    <a href="${pageContext.request.contextPath}/board/boardDetail?id=${board.id}" class="hover:underline">${fn:escapeXml(board.title)}</a>
                        <c:if test="${board.commentCount ge 10}">
                            <span class="bg-red-100 text-red-800 text-xs font-medium me-2 px-2.5 py-0.5
                            rounded dark:bg-gray-700 dark:text-red-400 border border-red-400">${board.commentCount}</span>
                        </c:if>
                        <c:if test="${board.commentCount gt 0 && board.commentCount lt 10}">
                            <span class="bg-gray-100 text-gray-800 text-xs font-medium me-2 px-2.5 py-0.5
                            rounded dark:bg-gray-700 dark:text-gray-400 border border-gray-500">${board.commentCount}</span>
                        </c:if>
                </td>
                <td class="px-6 py-4">${board.memberId}</td>
                <td class="px-6 py-4">
                     <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd" var="regDate" scope="page"/>
                     <fmt:formatDate value="${regDate}" pattern="yyyy/MM/dd"/>
                </td>
                <td class="px-6 py-4">
                    <c:if test="${board.attachCount gt 0}">
                        <img class="w-[16px]" src="../images/file.png" alt="">
                    </c:if>
                </td>
                <td class="px-6 py-4">${board.readCount}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="flex justify-center mt-6">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            <%-- 생성한 pagebar --%>
            ${pagebar}
        </ul>
    </nav>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>