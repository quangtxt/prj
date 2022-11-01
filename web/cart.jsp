<%@include file="template/header.jsp" %>
<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART <p style="color: green">${msgSuccessfull}</p></h3>
        </div>
        <div id="cart-content">
            <c:forEach items="${cart.entrySet()}" var="m" >
                <c:set var="p" value="${productDAO.getProductsByID(m.getKey())}"/>
                <div class="cart-item">
                    <div class="cart-item-infor">
                        <div class="cart-item-img">
                            <img src="<%=path%>/img/1.jpg"/>
                        </div>
                        <div class="cart-item-name">
                            <a href="<%=path%>/product/detail?ID=${p.getProductID()}">${p.getProductName()}</a>
                        </div>
                        <div class="cart-item-price">
                            ${p.getUnitPrice()*m.getValue()}
                            <c:set var="total" value="${total + p.getUnitPrice()*m.getValue()}"/>
                        </div>
                        <div class="cart-item-button">
                            <a href="cart?ID=${p.getProductID()}&action=remove">Remove</a>
                        </div>
                    </div>
                    <div class="cart-item-function">
                        <a class="sub" href="#" onclick="sub('${p.getProductID()}', '${m.getValue()}')">-</a>  
                        <a href="cart?ID=${p.getProductID()}&action=add">+</a>
                        <input type="text" value="${m.getValue()}" name="cart-item" disabled/>
                    </div>
                </div>
            </c:forEach>    
        </div>
        <c:if test="${total!=null}">
            <div id="cart-summary">
                <div id="cart-summary-content">Total amount: <span style="color:red">${total}</span></div>
            </div></c:if>
            <form method="post">
                <div id="customer-info">
                    <div id="customer-info-content">
                        <h3>CUSTOMER INFORMATION:</h3>
                    <c:if test="${msgError!=null}"> <span class="msg-error">${msgError}<br/></span> </c:if>
                        <div id="customer-info-detail">
                        <c:if test="${cust != null}">
                            <div id="customer-info-left">
                                <input type="text" value="${cust.getCompanyName()}" name="txtCompanyName" disabled/><br/>
                                <input type="text" value="${cust.getContactName()}" name="txtContactName" disabled/><br/>
                                <input type="date" name="txtRequiredDate"/><br/>
                                <c:if test="${msg!=null}">
                                    <span class="msg-error">${msg}<br/></span>
                                    </c:if>
                            </div>
                            <div id="customer-info-right">
                                <input type="text" value="${cust.getContactTitle()}" name="txtContactTitle" disabled/><br/>
                                <input type="text" value="${cust.getAddress()}" name="txtAddress" disabled/><br/>
                            </div>
                        </c:if>
                        <c:if test="${cust == null}">
                            <div id="customer-info-left">
                                <input type="text" placeholder="Company name *" name="txtCompanyName" /><br/>
                                <input type="text" placeholder="Contact name *" name="txtContactName"/><br/>
                                <input type="date" name="txtRequiredDate"/><br/>
                                <c:if test="${msg!=null}">
                                    <span class="msg-error">${msg}<br/></span>
                                    </c:if>
                            </div>
                            <div id="customer-info-right">
                                <input type="text" placeholder="Contact title *" name="txtContactTitle"/><br/>
                                <input type="text" placeholder="Address *" name="txtAddress"/><br/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>PAYMENT METHODS:</h3>
                    <div id="customer-info-payment">
                        <div>
                            <input type="radio" name="rbPaymentMethod" checked/>
                            Payment C.O.D - Payment on delivery
                        </div>
                        <div>
                            <input type="radio" name="rbPaymentMethod" disabled/>
                            Payment via online payment gateway
                        </div>
                    </div>
                </div>
            </div>
            <div style="margin: 20px" id="cart-order">
                <input type="submit" value="ORDER"/>
            </div>
        </form>
    </div>
</div>
<%@include file="template/footer.jsp" %>
<script type="text/javascript">
    function sub(ID, quantity) {
        var sub = document.getElementsByClassName('sub');
        if (parseInt(quantity) === 1) {
            if (window.confirm("Are you sure you want to quit this product?")) {
                for (var i = 0; i < sub.length; i++) {
                    sub[i].href = "cart?ID=" + ID + "&action=remove";
                }
            } else {
                for (var i = 0; i < sub.length; i++) {
                    sub[i].href = "cart?ID=" + ID;
                }
            }
        } else {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href = "cart?ID=" + ID + "&action=sub";
            }
        }
    }
</script>
