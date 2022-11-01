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
            <a href="profile?order=list"><li>All orders</li></a>
            <a href="profile?order=cancel"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">LIST ORDERS</b></div>
        <div class="content-main">
            <div id="profile-content-order">
                <c:if test="${oCancel!=null}">
                    <c:set var="i" value="1"/>
                    <c:forEach items="${oCancel}" var="ord" >
                        <div>
                            <div class="profile-order-title">
                                <div class="profile-order-title-left">
                                    <div>Order creation date: ${ord.getOrderDate()}</div>
                                    <div>Order: <a class="order-id" onclick="display(${ord.getOrderID()})" href="#">#${ord.getOrderID()}</a></div>
                                </div>
                                <div class="profile-order-title-right">
                                    <span style="color: red;">Order Canceled</span>
                                </div>
                            </div>
                            <c:if test="${i==1}">
                                <c:set var="str" value="profile-order-content"/>
                            </c:if>
                            <c:if test="${i!=1}">
                                <c:set var="str" value="order-detail profile-order-content ${ord.getOrderID()}"/>
                            </c:if>
                            <c:forEach items="${OrderDAO.getOrderDetails(ord.getOrderID())}" var="oDetail">
                                <div class="${str}">
                                    <div class="profile-order-content-col1">
                                        <a href="<%=path%>/product/detail?ID=${oDetail.getProductID()}"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                                    </div>
                                    <div class="profile-order-content-col2">${ProductDAO.getProductsByID(oDetail.getProductID()).getProductName()}</div>
                                    <div class="profile-order-content-col3">Quantity: ${oDetail.getQuantity()}</div>
                                    <div class="profile-order-content-col4">${oDetail.getUnitPrice()} $</div>
                                </div>
                            </c:forEach>
                            <c:set var="i" value="2"/>
                        </div>
                    </c:forEach>
                </c:if>
                <c:set var="i" value="1"/>
                <c:forEach items="${oPending}" var="ord" >
                    <div>
                        <div class="profile-order-title">
                            <div class="profile-order-title-left">
                                <div>Order creation date: ${ord.getOrderDate()}</div>
                                <div>Order: <a class="order-id" href="#" onclick="display(${ord.getOrderID()})">#${ord.getOrderID()}</a></div>
                            </div>
                            <div class="profile-order-title-right">
                                <span>Pending | <a href="" class="cancel" onclick="doCancel(${ord.getOrderID()})">Cancel</a> </span>
                            </div>
                        </div>
                        <c:if test="${i==1}">
                            <c:set var="str" value="profile-order-content"/>
                        </c:if>
                        <c:if test="${i!=1}">
                            <c:set var="str" value="order-detail profile-order-content ${ord.getOrderID()}"/>
                        </c:if>
                        <c:forEach items="${OrderDAO.getOrderDetails(ord.getOrderID())}" var="oDetail">
                            <div class="${str}">
                                <div class="profile-order-content-col1">
                                    <a href="<%=path%>/product/detail?ID=${oDetail.getProductID()}"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                                </div>
                                <div class="profile-order-content-col2">${ProductDAO.getProductsByID(oDetail.getProductID()).getProductName()}</div>
                                <div class="profile-order-content-col3">Quantity: ${oDetail.getQuantity()}</div>
                                <div class="profile-order-content-col4">${oDetail.getUnitPrice()} $</div>
                            </div>
                        </c:forEach>
                        <c:set var="i" value="2"/>
                    </div>
                </c:forEach>
                <c:set var="i" value="1"/>
                <c:forEach items="${oComplete}" var="ord" >
                    <div>
                        <div class="profile-order-title">
                            <div class="profile-order-title-left">
                                <div>Order creation date: ${ord.getOrderDate()}</div>
                                <div>Order: <a class="order-id" href="#" onclick="display(${ord.getOrderID()})">#${ord.getOrderID()}</a></div>
                            </div>
                            <div class="profile-order-title-right">
                                <span style="color: blue;">Completed</span>
                            </div>
                        </div>
                        <c:if test="${i==1}">
                            <c:set var="str" value="profile-order-content"/>
                        </c:if>
                        <c:if test="${i!=1}">
                            <c:set var="str" value="order-detail profile-order-content ${ord.getOrderID()}"/>
                        </c:if>
                        <c:forEach items="${OrderDAO.getOrderDetails(ord.getOrderID())}" var="oDetail">
                            <div class="${str}">
                                <div class="profile-order-content-col1">
                                    <a href="<%=path%>/product/detail?ID=${oDetail.getProductID()}"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                                </div>
                                <div class="profile-order-content-col2">${ProductDAO.getProductsByID(oDetail.getProductID()).getProductName()}</div>
                                <div class="profile-order-content-col3">Quantity: ${oDetail.getQuantity()}</div>
                                <div class="profile-order-content-col4">${oDetail.getUnitPrice()} $</div>
                            </div>
                        </c:forEach>
                        <c:set var="i" value="2"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>


<script>
    $(document).ready(function () {
        $(".order-detail.profile-order-content").hide();
    });
</script>
<script type="text/javascript">
    function doCancel(ID) {
        var sub = document.getElementsByClassName('cancel');
        if (window.confirm("Are you sure about that?")) {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href = "profile?order=cancel&ID=" + ID;
            }
        } else {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href = "profile?order=list";
            }
        }
    }
    function display(ID) {
        $("." + ID).toggle(1000);
    }
</script>
