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
    <%@include file="/WEB-INF/jsp/blog/include/head.jsp" %>
</head>
<body>
<!-- Navigation -->
<%@include file="/WEB-INF/jsp/blog/include/nav.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8">

            <!-- Blog Post -->

            <!-- Title -->
            <h1>${blog.blogTitle}</h1>
            <hr>
            <!-- Date/Time -->
            <p><span class="glyphicon glyphicon-time"></span> Posted on  <fmt:formatDate value="${blog.updateTime}" type="both"   pattern="yyyy/MM/dd HH:mm:ss"/>  作者:<a href="index.php">${blog.blogUser.userName}</a></p>

            <hr>

            <!-- Preview Image -->
            <%--<c:if test="${!empty blog.blogImage}">
                <img class="img-responsive" src="${blog.blogImage}" alt="">
            </c:if>
            <hr>--%>

            <!-- Post Content -->
            <p class="lead">摘要：${blog.blogBrief}</p>
            ${blog.blogContent}
            <hr>

            <!-- Blog Comments -->

            <!-- Posted Comments -->
            <!-- Comment -->
            <%--<div class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src="http://placehold.it/64x64" alt="">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">Start Bootstrap
                        <small>August 25, 2014 at 9:30 PM</small>
                    </h4>
                    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo.
                    Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi
                    vulputate fringilla. Donec lacinia congue felis in faucibus.
                    <!-- Nested Comment -->
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" src="http://placehold.it/64x64" alt="">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Nested Start Bootstrap
                                <small>August 25, 2014 at 9:30 PM</small>
                            </h4>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin
                            commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce
                            condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                    </div>
                    <!-- End Nested Comment -->
                </div>
            </div>--%>
            <!--PC版-->
            <div id="SOHUCS" sid="blog_${blog.blogId}"></div>
            <script charset="utf-8" type="text/javascript"
                    src="https://changyan.sohu.com/upload/changyan.js"></script>
            <script type="text/javascript">
                window.changyan.api.config({
                    appid: 'cyt41ItsL',
                    conf: 'prod_892fe75589e3056d5f79d26919f03104'
                });
            </script>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <div class="col-md-4">
            <%@include file="/WEB-INF/jsp/blog/include/slidebar.jsp" %>
        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<!-- /.container -->

<%@include file="/WEB-INF/jsp/blog/include/foot.jsp" %>

</body>
</html>
