<%@include file="template/headerAdmin.jsp" %>
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form action="order" method="post">
                    From: <input type="date" name="txtStartOrderDate"/>
                    To: <input type="date" name="txtEndOrderDate"/>
                    <input type="submit" value="Filter">
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${listOrd}" var="p" >
                        <tr>
                            <td><a href="order-detail.jsp?id=${p.getOrderID()}">#${p.getOrderID()}</a></td>
                            <td>${p.getOrderDate()}</td>
                            <td>${p.getRequiredDate()}</td>
                            <td>${p.getShippedDate()}</td>
                            <c:forEach items="${listEmp}" var="emp">
                                <c:if test="${emp.getEmployeeID() == p.getEmployeeID()}">
                                    <td>${emp.getFirstName()}</td>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${listCus}" var="cus">
                                <c:if test="${cus.getCustomerID() == p.getCustomerID()}">
                                    <td>${cus.getContactName()}</td>
                                </c:if>
                            </c:forEach>
                            <td>${p.getFreight()}</td>
                            <c:if test="${p.getShippedDate()!=null}">
                                <td>Completed</td>
                            </c:if>
                            <c:if test="${p.getRequiredDate()==null }">
                                <td style="color: red;">Order Canceled</td>
                            </c:if>
                            <c:if test="${p.getShippedDate()==null && p.getRequiredDate()!=null}">
                                <td style="color: blue;">Pending | <a href="" id="cancel" onclick="doCancel(${p.getOrderID()})">Cancel</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>    
                </table>
            </div>
            <c:url var="paging" value="paging">    
                    <c:param name="prv" value="order"></c:param>
                    <c:if test="${sDate!=null||eDate!=null}">
                        <c:param name="sDate" value="${sDate}"></c:param>
                        <c:param name="eDate" value="${eDate}"></c:param>
                    </c:if>
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
<%@include file="template/footerAdmin.jsp" %>
<script type="text/javascript">
    function doCancel(ID) {
        var sub = document.getElementsByClassName('cancel');
        if (window.confirm("Are you sure about that?")) {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href = "order?ID=" + ID + "&action=cancel";
            }
        } else {
            for (var i = 0; i < sub.length; i++) {
                sub[i].href = "order?ID=" + ID;
            }
        }
    }
</script>
