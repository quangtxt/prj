<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp" %>
<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <ul>
            <c:forEach items="${list}" var="ca" >
                <c:url value="product" var="p">
                    <c:param name="cateID" value="${ca.getCategoryID()}"/>
                </c:url>
                <a href="${p}"><li>${ca.getCategoryName()}</li></a>
                    </c:forEach>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">${ca.getCategoryName()}</b><br/>
            
        </div>

        <div class="content-main"> 
            <c:forEach items="${products}" var="p" >
                <c:url value="product" var="edit">
                    <c:param name="action" value="edit"/>
                    <c:param name="ID" value="${p.getProductID()}"/>
                    <c:param name="cateID" value="${ca.getCategoryID()}"/>
                </c:url>
                <c:url value="product" var="remove">
                    <c:param name="action" value="remove"/>
                    <c:param name="ID" value="${p.getProductID()}"/>
                    <c:param name="cateID" value="${ca.getCategoryID()}"/>
                </c:url>
                <div class="product">
                    <a href=""><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="product/detail?ID=${p.getProductID()}">${p.getProductName()}</a></div>
                    <div class="price">$${p.getUnitPrice()}</div>
                    <div class="buyNow"><a href="account/cart?ID=${p.getProductID()}">Buy now</a></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>