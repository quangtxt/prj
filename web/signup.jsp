<%@include file="template/header.jsp" %>
<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="signup" style="color: red;">SIGN UP</a></span>
            <span><a href="signin">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <form action="signup" method="post">
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtCompanyName"/><br/>
                <c:if test="${msgCompanyName != null}">
                    <span class="msg-error">${msgCompanyName}<br/></span>
                </c:if> 
                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtContactName" /><br/>
                <c:if test="${msgContactName != null}">
                    <span class="msg-error">${msgContactName}<br/></span>
                </c:if> 
                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtContactTitle"/><br/>
                <label>Address<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtAddress"/><br/>
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtEmail"/><br/>
                <c:if test="${msgEmail != null}">
                    <span class="msg-error">${msgEmail}"<br/>
                    </span>
                </c:if> 
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtPassword"/><br/>
                <c:if test="${msgPass != null}">
                    <span class="msg-error">${msgPass}<br/>
                    </span>
                </c:if>
                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtRe-Password"/><br/>
                <c:if test="${msgRePass != null}">
                    <span class="msg-error">${msgRePass}<br/>
                    </span>
                </c:if>
                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>    
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
