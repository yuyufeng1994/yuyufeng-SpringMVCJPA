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
    <title>联系--心得站点</title>
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

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>留言</h3>
                </div>
                <div class="panel-body">
                   <p>如果您有任何关于本站的疑问，请发送邮件至下方地址或者在此页面进行留言</p>
                   <p>Email:<a href="mailto:yuyf123@foxmail.com">yuyf123@foxmail.com</a></p>
                </div>
                <!-- /.panel-body -->
            </div>
            <!--PC版-->
            <div id="SOHUCS" sid="contact_content"></div>
            <script charset="utf-8" type="text/javascript"
                    src="https://changyan.sohu.com/upload/changyan.js"></script>
            <script type="text/javascript">
                window.changyan.api.config({
                    appid: 'cyt41ItsL',
                    conf: 'prod_892fe75589e3056d5f79d26919f03104'
                });
            </script>
        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<!-- /.container -->
<%@include file="/WEB-INF/jsp/include/foot.jsp" %>

</body>
</html>
