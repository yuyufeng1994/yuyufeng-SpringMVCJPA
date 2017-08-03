<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/5/31
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <%@ include file="/WEB-INF/jsp/admin/include/head.jsp" %>
</head>
<body>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header text-center">yuyufeng.top's Admin</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><a href="javascript:history.go(-1)">返回</a></h3>
                </div>
                <div class="panel-body">
                    <div class="jumbotron">
                        <div class="alert alert-danger" role="alert"><i class="glyphicon glyphicon-warning-sign"></i> ${ex.message}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/admin/include/bodyfoot.jsp" %>


</body>
</html>
