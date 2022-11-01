<%@include file="template/header.jsp" %>
<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome,${cust.getContactName()}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="profile"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="../listOrder.jsp"><li>All orders</li></a>
            <a href="#"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">LIST ORDERS</b></div>
        <div class="content-main">
            <div id="profile-content-order">
                <c:choose>
                    <c:when test="${orders.isEmpty()}">
                        <div class="profile-order-title">
                            <div style="text-align: center" class="profile-order-title-left">
                                <span style="color: red;font-weight: bold">No product is in pending</b></span>
                            </div>
                            <div class="profile-order-title-right">
                                <span>Pending</span>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${orders}" var="order" >
                            <div class="order-detail">
                                <div class="profile-order-title">
                                    <div class="profile-order-title-left">
                                        <div>Order creation date: ${order.getOrderDate()}</div>
                                        <div>Order: <a href="#" class="order-id">#1</a></div>
                                    </div>
                                    <div class="profile-order-title-right">
                                        <span>Pending</span>
                                    </div>
                                </div>
                                <c:forEach items="${OrderDAO.getOrderDetailPending(order.getOrderID())}" var="detail">
                                    <div class="profile-order-content">
                                        <div class="profile-order-content-col1">
                                            <a href="<%=path%>/product/detail?ID=${detail.getProductID()}"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                                        </div>
                                        <div class="profile-order-content-col2">${ProductDAO.getProductsByID(detail.getProductID()).getProductName()}</div>
                                        <div class="profile-order-content-col2">Quantity: ${detail.getQuantity()}</div>
                                        <div class="profile-order-content-col4">Unit Price: ${detail.getUnitPrice()} $</div>
                                        <c:set var="totalPrice" value="${totalPrice+detail.getUnitPrice()*detail.getQuantity()}"/>
                                    </div>
                                </c:forEach>
                                <div class="profile-order-title">
                                    <div style="margin: 0 auto"> Total money: <c:out value="${totalPrice}" /></div>
                                </div>
                                <c:remove var="totalPrice"/>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
