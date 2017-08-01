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
<div id="wrapper">
    <!-- Navigation -->
    <%@ include file="/WEB-INF/jsp/admin/include/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">文章列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <table width="100%"
                       class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline collapsed"
                       id="dataTables-example" role="grid" aria-describedby="dataTables-example_info"
                       style="width: 100%;">
                    <thead>
                    <tr role="row">
                        <th>ID</th>
                        <th>标题</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--<c:forEach items="${page.list}" var="a">
                        <tr>
                            <td>${a.articleId}</td>
                            <td><a href="${appServer}/article/${a.articleId}" target="_blank">${a.articleTitle}</a></td>
                            <td><fmt:formatDate value="${a.articleTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><a href="${appServer}/admin/article/edit/${a.articleId}?pageNo=${page.pageNum}"
                                   class="btn btn-info">修改</a>
                                <button class="btn btn-danger"
                                        onclick="del('${appServer}/admin/article/delete/${a.articleId}?pageNo=${page.pageNum}')">
                                    删除
                                </button>
                                <button class="btn btn-primary"
                                        onclick="createIndex('${a.articleId}')">
                                    索引
                                </button>
                            </td>
                        </tr>
                    </c:forEach>--%>
                    </tbody>
                </table>
                <%--<!-- Pager -->
                <ul class="pagination">
                    <c:if test="${page.pageNum != 1}">
                        <li><a href="${appServer}/admin/article/manage/1">&laquo;</a></li>
                    </c:if>
                    <c:forEach items="${page.navigatepageNums}" var="c">
                        <c:if test="${c == page.pageNum}">
                            <li class="active">
                                <a>${c}</a>
                            </li>
                        </c:if>
                        <c:if test="${c != page.pageNum}">
                            <li>
                                <a href="${appServer}/admin/article/manage/${c}">${c}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${page.pageNum != page.pages}">
                        <li><a href="${appServer}/admin/article/manage/${page.pages}">&raquo;</a></li>
                    </c:if>

                </ul>--%>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
<%@ include file="/WEB-INF/jsp/admin/include/bodyfoot.jsp" %>


</body>
<script>
    $("#publish-button").click(function () {
        $("#articleContent").val(editor.txt.html())
        $("#my-form").submit();
    })


    function del(url) {
        if (window.confirm('你确定要删除吗？')) {
            window.location.href = url;
            return true;
        } else {
            return false;
        }
    }

    function createIndex(id) {
        $.post("${appServer}/admin/article/index/" + id, function (res) {
            alert(res)
        })
    }


</script>
</html>
