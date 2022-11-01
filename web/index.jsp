<%@include file="template/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<div id="content">
    <div id="content-left">
        <h3>CATEGORY ${test}</h3>
        <ul>
            <c:forEach items="${list}" var="ca">
                <c:url value="product" var="p">
                    <c:param name="cateID" value="${ca.getCategoryID()}"/>
                </c:url>
                <a href="${p}"><li>${ca.getCategoryName()}</li></a>
                    </c:forEach>
        </ul>
    </div>
    <div id="content-right">
        <c:if test="${search!=null}" >
            <div class="path" style="text-transform: none">Search results for "${search}"</div>
            <div class="content-main"> 
                <!--//4 sp co gia khuyen mai nhieu nhat-->
                <c:forEach items="${listPani}" var="p" >
                    <div class="product">
                        <a href=""><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                        <div class="price">$${p.getUnitPrice()}</div>
                        <div class="buyNow"><a href="account/cart?ID=${p.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
            <div id="paging">
                <div class="pagination">
                    <c:url var="paging" value="paging">
                        <c:param name="prv" value="home"></c:param>
                        <c:param name="txtSearch" value="${search}"></c:param>
                    </c:url>
                    <c:if test="${page!=null}" >
                        <c:choose>
                            <c:when test="${Integer.parseInt(page)>3 && Integer.parseInt(endP)>5}">
                                <a href="${paging}&page=1">&laquo;</a>
                                <a href="${paging}&page=${page-1}">&lt;</a>
                            </c:when>
                            <c:when test="${Integer.parseInt(page)>=2&& Integer.parseInt(endP)>5}">
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
        </c:if>
        <c:if test="${search==null}" >
            <div class="path">Hot</b></div>
            <div class="content-main"> 
                <!--//4 sp co gia khuyen mai nhieu nhat-->
                <c:forEach items="${products1}" var="p" >
                    <div class="product">
                        <a href=""><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                        <div class="price">$${p.getUnitPrice()}</div>
                        <div class="buyNow"><a href="account/cart?ID=${p.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
            <div class="path">Best Sale</b></div>
            <div class="content-main"> 
                <!--4 sp co so luong trong order detail nhieu nhat dc ban cho nhieu ng nhat-->
                <c:forEach items="${products2}" var="p" >
                    <div class="product">
                        <a href=""><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                        <div class="price">$${p.getUnitPrice()}</div>
                        <div class="buyNow"><a href="account/cart?ID=${p.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
            <div class="path">New Product</b></div>
            <div class="content-main">
                <!--top 4 sp moi nhat--> 
                <c:forEach items="${products3}" var="p" >
                    <div class="product">
                        <a href=""><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                        <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                        <div class="price">$${p.getUnitPrice()}</div>
                        <div class="buyNow"><a href="account/cart?ID=${p.getProductID()}">Buy now</a></div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>

<%@include file="template/footer.jsp" %>