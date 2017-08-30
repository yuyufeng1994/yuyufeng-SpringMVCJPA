<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/8/1
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <title>服务 -- 心得站点</title>
    <%@include file="/WEB-INF/jsp/include/head.jsp" %>
</head>
<body>
<!-- Navigation -->
<%@include file="/WEB-INF/jsp/include/nav.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-12">
            <h1 class="my-4">服务
                <small>更多接口,即将上线</small>
            </h1>


            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title">二维码生成服务</h2>
                    <p class="card-text">输入相应内容,即可生成二维码</p>
                    <a href="${appServer}/service/toQrcode" class="btn btn-primary">立即使用 &rarr;</a>
                </div>
            </div>

        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<!-- /.container -->
<%@include file="/WEB-INF/jsp/include/foot.jsp" %>

</body>
</html>
