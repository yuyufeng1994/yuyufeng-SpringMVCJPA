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
    <title>${blog.blogTitle}--博客--心得站点</title>
    <%@include file="/WEB-INF/jsp/include/head.jsp" %>
</head>
<body>
<!-- Navigation -->
<%@include file="/WEB-INF/jsp/include/nav.jsp" %>

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
            <p><span class="glyphicon glyphicon-time"></span>  更新于  <fmt:formatDate value="${blog.updateTime}" type="both"   pattern="yyyy/MM/dd HH:mm:ss"/>  作者:<a href="index.php">${blog.blogUser.userName}</a></p>
            <p><c:forEach items="${blog.catalogs}" var="c">
                <a class="label label-primary" href="${appServer}/blog/list-catalog/${c.catalogId}/1">${c.catalogName}</a>
            </c:forEach></p>
            <hr>
            <!-- Post Content -->
            <p class="lead">${blog.blogBrief}</p>
            ${blog.blogContent}
            <hr>

            <!-- Blog Comments -->
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
<%@include file="/WEB-INF/jsp/include/foot.jsp" %>

</body>
</html>
