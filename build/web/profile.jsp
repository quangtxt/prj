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
            <div id="profile-content">
                <div class="profile-content-col">
                    <div>Company name: <br/>${cust.getCompanyName()}</div>
                    <div>Contact name: <br/>${cust.getContactName()}</div>
                    <form action="profile" method="post">
                        <div>
                            <input type="submit" value="Edit info"/>
                        </div>
                    </form>
                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/>${cust.getContactTitle()}</div>
                    <div>Address: <br/>${cust.getAddress()}</div>
                </div>
                <div class="profile-content-col">
                    <div>Email: <br/>${acc.getEmail()}</div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
