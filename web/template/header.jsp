<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="dal.*" %>
<%@page import="models.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Web</title>
        <% String path = request.getContextPath();%>
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
        <script src="<%=path%>/lib/jquery/dist/jquery.min.js"></script>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%=path%>/home"><img src="<%=path%>/img/logo.png"/></a>
                </div>
                <c:if test="${list!=null}">
                    <div id="product-title-2" style="width: 100%;display: inline-block;padding-left: 30px;padding-top: 75px">
                        <form action="home" method="post">
                            <input type="text" name="txtSearch" value="${search}" placeholder="Enter product name to search"/>
                            <input type="submit" value="Search">
                        </form>
                    </div>   
                </c:if>
                <div id="banner">          
                    <ul>
                        <c:if test="${cart!=null}">
                            <c:set var="size" value="${cart.size()}"/>
                        </c:if>
                        <c:if test="${cart==null}">
                            <c:set var="size" value="0"/>
                        </c:if>
                        <li><a href="<%=path%>/account/cart">Cart: ${size}</a></li>
                            <c:choose>
                                <c:when test="${AccSession == null}">
                                <li><a href="<%=path%>/account/signin">SignIn</a></li>
                                <li><a href="<%=path%>/account/signup">SignUp</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="<%=path%>/account/profile">Profile: ${uname}</a></li>
                                <li><a href="<%=path%>/account/signin">SignOut</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div>
            </div>
