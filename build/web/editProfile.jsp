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
        <div class="path">Personal information</b></div>
        <div class="content-main">
            <form id="content-main-product" action="profile" method="post">
                <div id="profile-content">
                    <div class="profile-content-col">
                        <label>Company name:<span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtCompanyName" value="${cust.getCompanyName()}" /><br/>
                        <label>Contact name: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtContactName" value="${cust.getContactName()}" /><br/>
                        <span style="color: red;">${err}</span>
                        <div>
                            <input type="submit" value="Save"/>
                        </div>
                        <input type="hiden" name="action" value="edit">
                    </div>
                    <div class="profile-content-col">
                        <label>Company title: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtCompanyTitle" value="${cust.getContactTitle()}" /><br/>
                        <label>Address: <span style="color: red;">*</span></label><br/>
                        <input type="text" name="txtAddress" value="${cust.getAddress()}" /><br/>
                    </div>
                    <div class="profile-content-col">
                        <div>Email: <br/>${acc.getEmail()}</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>
