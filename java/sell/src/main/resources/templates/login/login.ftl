<html>
<#include "../common/header.ftl">

<body>
<div class="container">
    <div class="row clearfix">
        <h1>用户登录</h1>
        <div class="col-md-12 column">
            <form role="form" method="post" action="/sell/seller/login">
                <div class="form-group">
                    <label>请输入用户名</label>
                    <input name="username" type="text" class="form-control" placeholder="请输入用户名"/>
                </div>
                <div class="form-group">
                    <label>请输入密码</label>
                    <input name="password" type="password" class="form-control" placeholder="请输入密码"/>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>