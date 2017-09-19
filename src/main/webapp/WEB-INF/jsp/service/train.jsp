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
    <title>火车票查询--服务--心得站点</title>
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
            <h1 class="my-4">二维码生成服务</h1>
            <table class="table table-bordered">
                <tr>
                    <th>请求地址</th>
                    <td>${appServer}/service/doQrcode</td>
                </tr>
                <tr>
                    <th>参数</th>
                    <th>说明</th>
                </tr>
                <tr>
                    <td>content</td>
                    <td>要生成二维码的内容</td>
                </tr>
                <tr>
                    <th>返回</th>
                    <td>图片流</td>
                </tr>
            </table>


        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<!-- /.container -->
<%@include file="/WEB-INF/jsp/include/foot.jsp" %>

</body>
<script>

</script>
</html>
