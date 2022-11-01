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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <% String path = request.getContextPath();%>
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo-admin">
                     Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>    
                                <li><a href="<%=path%>/account/signin">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <c:url value="product" var="list">
                <c:param name="action" value="list"/>
            </c:url>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <a href="dashboard.jsp"><li>Dashboard</li></a>
                        <a href="order"><li>Orders</li></a>
                        <a href="${list}"><li>Products</li></a>
                        <a href="#"><li>Customers</li></a>
                    </ul>
                </div>
