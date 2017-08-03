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
                <h3>
                    <a href="${appServer}/blog/content/${b.blogId}">${b.blogTitle}</a>
                </h3>
                <p>${b.blogBrief}</p>
                <p><span class="glyphicon glyphicon-time"></span> 发表于 <fmt:formatDate value="${b.updateTime}" type="both"   pattern="yyyy/MM/dd HH:mm:ss"/> | 作者：<a href="index.php">${b.blogUser.userName}</a> | 评论数：<span id = "sourceId::blog_${b.blogId}" class = "cy_cmt_count" ></span></p>
                <c:if test="${!empty b.blogImage}">
                    <img class="img-responsive" src="${b.blogImage}" alt="">
                    <br/>
                </c:if>

                <c:forEach items="${b.catalogs}" var="c">
                    <a class="label label-primary" href="${appServer}/blog/list-catalog/${c.catalogId}/1">${c.catalogName}</a>
                </c:forEach>

                <script id="cy_cmt_num" src="https://changyan.sohu.com/upload/plugins/plugins.list.count.js?clientId=cyt41ItsL">
                </script>
                <hr>
            </c:forEach>

            <!-- Pager -->
            <ul class="pager">
                <c:if test="${!page.first}">
                    <li class="previous">
                        <a href="${appServer}/blog/list/${page.number}">&larr; 上一页</a>
                    </li>
                </c:if>
                <c:if test="${!page.last}">
                    <li class="next">
                        <a href="${appServer}/blog/list/${page.number+2}">下一页 &rarr;</a>
                    </li>
                </c:if>
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
