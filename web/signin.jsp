x<%@include file="template/header.jsp" %>
<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="signup">SIGN UP</a></span>
            <span><a href="signin" style="color: red;">SIGN IN</a></span>
        </div>
        <div id="form-content">
            <form action="signin" method="post">
                <c:if test="${msg != null}">
                    <span class="msg-error">${msg}<br/></span>
                </c:if>
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtEmail"/><br/>
                <c:if test="${msgEmail != null}">
                    <span class="msg-error">${msgEmail}<br/>
                    </span>
                </c:if> 
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtPassword"/><br/>
                <c:if test="${msgPass != null}">
                    <span class="msg-error">${msgPass}<br/>
                    </span>
                </c:if>
                <div><a href="../forgot.jsp">Forgot password?</a></div>
                <div class="error" style="color: red">
                </div>
                <input type="submit" value="SIGN IN"/><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
