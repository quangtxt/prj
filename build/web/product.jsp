<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/headerAdmin.jsp" %>
<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div> ${test}
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form action="product" method="post">
                        <select name="txtCategoryID">
                            <c:forEach items="${cateLists}" var="ca">
                                <c:choose>
                                    <c:when test="${ca.getCategoryID()==categoryID}">
                                        <option value="${ca.getCategoryID()}" selected>${ca.getCategoryName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ca.getCategoryID()}">${ca.getCategoryName()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>            
                        </select>
                        <input type="submit" value="Filter">
                        <input type="hidden" name="action" value="list">
                    </form>
                </div>
                <c:url value="product" var="create">
                    <c:param name="action" value="create"/>
                </c:url>
                <div id="product-title-2" style="width: 55%;">
                    <form action="product" method="post">
                        <input type="text" name="txtSearch" value="${search}" placeholder="Enter product name to search"/>
                        <input type="submit" value="Search">
                        <input type="hidden" name="action" value="list">
                    </form>
                </div>
                <div id="product-title-3" style="width: 20%;">
                    <a href="${create}">Create a new Product</a>
                    <form action="">
                        <label for="upload-file">Import .xls or .xlsx file</label>
                        <input type="file" name="file" id="upload-file" />
                    </form>
                </div>
            </div>
            <div id="order-table-admin">
                <c:if test="${msg!=null}"><p style="color: red;text-align: center">${msg}</p></c:if>
                    <table id="orders">
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>UnitPrice</th>
                            <th>Unit</th>
                            <th>UnitsInStock</th>
                            <th>Category</th>
                            <th>Discontinued</th>
                            <th style="width: 130px;"></th>
                        </tr>

                    <c:forEach items="${proLists}" var="p" >
                        <c:url value="product" var="edit">
                            <c:param name="action" value="edit"/>
                            <c:param name="ID" value="${p.getProductID()}"/>
                        </c:url>
                        <c:url value="product" var="delete">
                            <c:param name="action" value="delete"/>
                            <c:param name="ID" value="${p.getProductID()}"/>
                        </c:url>
                        <tr>
                            <td><a href="order-detail.jsp?id=${p.getProductID()}">#${p.getProductID()}</a></td>
                            <td>${p.getProductName()}</td>
                            <td>${p.getUnitPrice()}</td>
                            <td>${p.getQuantityPerUnit()}</td>
                            <td>${p.getUnitsInStock()}</td>
                            <c:forEach items="${cateLists}" var="cate">
                                <c:if test="${cate.getCategoryID() == p.getCategoryID()}">
                                    <td>${cate.getCategoryName()}</td>
                                </c:if>
                            </c:forEach>
                            <c:if test="${p.isDiscontinued()}">
                                <td>Discontinued</td>
                            </c:if>
                            <c:if test="${!p.isDiscontinued()}">
                                <td>Continued</td>
                            </c:if>
                            <td>
                                <a href="${edit}">Edit</a> &nbsp; | &nbsp; 
                                <a href="" class="cancel" onclick="doDelete('${delete}')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <c:url var="paging" value="paging">
                <c:choose>
                    <c:when test="${search!=null}">
                        <c:param name="txtSearch" value="${search}"></c:param>              
                    </c:when>
                    <c:when test="${categoryID!=null}">
                        <c:param name="txtCategoryID" value="${categoryID}"></c:param>
                    </c:when>
                    <c:otherwise>
                        <c:param name="action" value="list"></c:param>
                    </c:otherwise>
                </c:choose>
            </c:url>
            <div id="paging">
                <div class="pagination">
                    <c:if test="${page!=null && Integer.parseInt(endP)>5}" >
                        <c:choose>
                            <c:when test="${Integer.parseInt(page)>3}">
                                <a href="${paging}&page=1">&laquo;</a>
                                <a href="${paging}&page=${page-1}">&lt;</a>
                            </c:when>
                            <c:when test="${Integer.parseInt(page)>=2}">
                                <a href="${paging}&page=${page-1}">&lt;</a>
                            </c:when> 
                        </c:choose>
                    </c:if>
                    <c:choose>
                        <c:when test="${Integer.parseInt(endP)>5}">
                            <c:choose>
                                <c:when test="${Integer.parseInt(page)<Integer.parseInt(endP)-2&&Integer.parseInt(page)>3}">
                                    <c:set var="start" value="${Integer.parseInt(page)-2}"/>
                                    <c:set var="end" value="${Integer.parseInt(page)+2}"/>
                                </c:when>
                                <c:when test="${Integer.parseInt(page)<=3}">
                                    <c:set var="start" value="1"/>
                                    <c:set var="end" value="${Integer.parseInt(page)+2}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="start" value="${Integer.parseInt(page)-2}"/>
                                    <c:set var="end" value="${endP}"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:set var="start" value="1"/>
                            <c:set var="end" value="${endP}"/>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${start}" end="${end}">
                        <c:if test="${i==page}">
                            <a href="${paging}&page=${i}" class="active">${i}</a>
                        </c:if>
                        <c:if test="${i!=page}">
                            <a href="${paging}&page=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${Integer.parseInt(endP)>5}" >
                        <c:choose>
                            <c:when test="${Integer.parseInt(page)==Integer.parseInt(endP)}">
                            </c:when>
                            <c:when test="${Integer.parseInt(page)>=Integer.parseInt(endP)-2}">
                                <a href="${paging}&page=${page+1}">&gt;</a>
                            </c:when> 
                            <c:otherwise>
                                <a href="${paging}&page=${page+1}">&gt;</a>
                                <a href="${paging}&page=${endP}">&raquo;</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function doDelete(del) {
        var sub = document.getElementsByClassName('cancel');
        if (window.confirm("Are you sure about that?")) {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href=del; 
            }
        } else {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href="product?action=list"; 
            }
        }
    }
</script>
<%@include file="template/footerAdmin.jsp" %>