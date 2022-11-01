<%@include file="template/headerAdmin.jsp" %>
<div id="content-right">
    <div class="path-admin">UPDATE PRODUCT</b></div>
    <div class="content-main">
        <form id="content-main-product" method="post">
            <div class="content-main-1">
                <label>ProductID<span style="color: red;">*</span></label><br/>
                <input type="text" name="ID" value="${p.getProductID()}" readonly/><br/>

                <label>ProductName<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtProductName" value="${p.getProductName()}"/><br/>

                <label>CategoryID<span style="color: red;">*</span></label><br/>
                <select name="txtCategoryID">
                    <c:forEach items="${cate}" var="ca">
                        <c:choose>
                            <c:when test="${ca.getCategoryID()==p.getCategoryID()}">
                                <option value="${ca.getCategoryID()}" selected>${ca.getCategoryName()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${ca.getCategoryID()}">${ca.getCategoryName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>             
                </select> <br/>

                <label>QuantityPerUnit<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtQuantityPerUnit" value="${p.getQuantityPerUnit()}"/><br/>

                <label>UnitPrice<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtUnitPrice" value="${p.getUnitPrice()}"/><br/>
            </div>
            <div class="content-main-1">
                <label>UnitsInStock<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtUnitsInStock" value="${p.getUnitsInStock()}"/><br/>

                <label>UnitsOnOrder<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtUnitsOnOrder" value="${p.getUnitsOnOrder()}"/><br/>
                <label>ReorderLevel<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtReorderLevel" value="${p.getReorderLevel()}"/><br/>
                <label>Discontinued<span style="color: red;">*</span></label><br/>
                <input type="radio" name="txtDiscontinued" value="${p.isDiscontinued()}" checked/>${p.isDiscontinued()}<br/>
                <input type="radio" name="txtDiscontinued" value="${!p.isDiscontinued()}"/>${!p.isDiscontinued()}<br/>
                <span style="color: red;">${err}</span><br/>
                <input type="submit" value="UPDATE"/><br/>
            </div>
        </form>
    </div>
</div>
</div>

<%@include file="template/footerAdmin.jsp" %>
