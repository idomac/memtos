<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true" description="分页" %>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false" description="每页大小" %>
<%@ attribute name="simple" type="java.lang.Boolean" required="false" description="是否简单风格" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="es" tagdir="/WEB-INF/tags" %>

<c:if test="${empty pageSize}">
    <c:set var="pageSize" value="${page.size}"/>
</c:if>

<c:set var="displaySize" value="2"/>

<c:set var="current" value="${page.number + 1}"/>

<c:set var="begin" value="${current - displaySize}"/>

<c:if test="${begin <= displaySize}">
    <c:set var="begin" value="${1}"/>
</c:if>

<c:set var="end" value="${current + displaySize}"/>

<c:if test="${end > page.totalPages - displaySize}">
    <c:set var="end" value="${page.totalPages - displaySize}"/>
</c:if>

<c:if test="${end < 0 or page.totalPages < displaySize * 4}">
    <c:set var="end" value="${page.totalPages}"/>
</c:if>

<div class="table-pagination <c:if test='${simple ne false}'> row-fluid tool ui-toolbar</c:if>">
    <div class="pagination">
        <table>
            <tr>
                <c:choose>
                    <c:when test="${page.firstPage}">
                        <td class="disabled"><a title="首页">首页</a></td>
                        <td class="disabled"><a title="上一页">&lt;&lt;</a></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="#" onclick="" title="首页">首页</a></td>
                        <td><a href="#" onclick="" title="上一页">&lt;&lt;</a></td>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="1" end="${begin == 1 ? 0 : 2}" var="i">
                    <td <c:if test="${current == i}"> class="active"</c:if>>
                        <a href="#" onclick="" title="第${i}页">${i}</a>
                    </td>
                </c:forEach>

                <c:if test="${begin > displaySize + 1}">
                    <td><a>...</a></td>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" var="i">
                    <td <c:if test="${current == i}"> class="active"</c:if>>
                        <a href="#" onclick="" title="第${i}页">${i}</a>
                    </td>
                </c:forEach>


                <c:if test="${end < page.totalPages - displaySize}">
                    <td><a>...</a></td>
                </c:if>

                <c:forEach begin="${end < page.totalPages ? page.totalPages - 1 : page.totalPages + 1}" end="${page.totalPages}" var="i">
                    <td <c:if test="${current == i}"> class="active"</c:if>>
                        <a href="#" onclick="" title="第${i}页">${i}</a>
                    </td>
                </c:forEach>

                <c:choose>
                    <c:when test="${page.lastPage}">
                        <td class="disabled"><a title="下一页">&gt;&gt;</a></td>
                        <td class="disabled"><a title="尾页">尾页</a></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="#" onclick="" title="下一页">&gt;&gt;</a></td>
                        <td><a href="#" onclick="" title="尾页">尾页</a></td>
                    </c:otherwise>
                </c:choose>

                <td>
                    <span class="page-input" style="margin-left: 20px;">
                    第<input type="text" style="width: 30px;" value="${current}" onblur=""/>页
                    </span>
                    <select class="input-small" onchange="">
                        <option value="10" <c:if test="${pageSize eq 10}">selected="selected" </c:if>>10</option>
                        <option value="20" <c:if test="${pageSize eq 20}">selected="selected" </c:if>>20</option>
                        <option value="30" <c:if test="${pageSize eq 30}">selected="selected" </c:if>>30</option>
                        <option value="50" <c:if test="${pageSize eq 50}">selected="selected" </c:if>>50</option>
                    </select>
                    <span class="page-info">[共${page.totalPages}页/${page.totalElements}条]</span >
                </td>
            </tr>
        </table>
    </div>
</div>


