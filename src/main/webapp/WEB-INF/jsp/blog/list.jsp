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
    <title>${catalog.catalogName} -- 心得站点</title>
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

            <h3 class="page-header">
                [${catalog.catalogName}]
                <small>${catalog.catalogBrief}</small>
            </h3>

            <c:forEach items="${page.content}" var="b">
                <!-- First Blog Post -->
                <h3>
                    <a href="${appServer}/blog/content/${b.blogId}">${b.blogTitle}</a>
                </h3>
                <p>${b.blogBrief}</p>
                <c:if test="${!empty b.blogImage}">
                    <img class="img-responsive" src="${b.blogImage}" alt="图片无法加载">
                    <br/>
                </c:if>
                <p><span class="glyphicon glyphicon-time"></span> 更新于 <fmt:formatDate value="${b.updateTime}" type="both"   pattern="yyyy/MM/dd HH:mm:ss"/> 作者 <a href="index.php">${b.blogUser.userName}</a> </p>
                <c:forEach items="${b.catalogs}" var="c">
                    <a class="label label-primary" href="${appServer}/blog/list-catalog/${c.catalogId}/1">${c.catalogName}</a>
                </c:forEach>
                <hr>
            </c:forEach>

            <!-- Pager -->
            <ul class="pager">
                <c:if test="${!page.first}">
                    <li class="previous">
                        <a href="${appServer}/blog/list-catalog/${catalog.catalogId}/${page.number}">&larr; 上一页</a>
                    </li>
                </c:if>
                <c:if test="${!page.last}">
                    <li class="next">
                        <a href="${appServer}/blog/list-catalog/${catalog.catalogId}/${page.number+2}">下一页 &rarr;</a>
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
