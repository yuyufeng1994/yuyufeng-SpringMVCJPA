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

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="page-header">
                博客
                <small>yuyufeng's blog</small>
            </h1>

            <c:forEach items="${page.content}" var="b">
                <!-- First Blog Post -->
                <h2>
                    <a href="${appServer}/blog/content/${b.blogId}">${b.blogTitle}</a>
                </h2>
                <p class="lead">
                    作者： <a href="index.php">${b.blogUser.userName}</a>
                </p>
                <p><span class="glyphicon glyphicon-time"></span> Posted on <fmt:formatDate value="${b.updateTime}" type="both"   pattern="yyyy/MM/dd HH:mm:ss"/></p>
                <hr>
                <c:if test="${!empty b.blogImage}">
                    <img class="img-responsive" src="${b.blogImage}" alt="">
                    <hr>
                </c:if>

                <p>${b.blogBrief}</p>
                <a class="btn btn-primary" href="${appServer}/blog/content/${b.blogId}">查看更多 <span
                        class="glyphicon glyphicon-chevron-right"></span></a>

                <hr>
            </c:forEach>

            <!-- Pager -->
            <ul class="pager">
                <li class="previous">
                    <a href="#">&larr; Older</a>
                </li>
                <li class="next">
                    <a href="#">Newer &rarr;</a>
                </li>
            </ul>

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
