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
                <h1 class="page-header">博客列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="btn-group" role="group" aria-label="...">
                    <a type="button" class="btn btn-default" href="${appServer}/admin/blog/save?returnUrl=${appServer}/admin/blog/list/${page.number+1}"><i class="glyphicon glyphicon-plus-sign"></i> 增加博客</a>
                </div>

                <table width="100%"
                       class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline collapsed"
                       id="dataTables-example" role="grid" aria-describedby="dataTables-example_info"
                       style="width: 100%;margin-top: 10px">
                    <thead>
                    <tr role="row">
                        <th>ID</th>
                        <th>标题</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.content}" var="b">
                        <tr>
                            <td>${b.blogId}</td>
                            <td><a href="${appServer}/blog/content/${b.blogId}" target="_blank">${b.blogTitle}</a></td>
                            <td><fmt:formatDate value="${b.updateTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <a type="button" class="btn btn-default"
                                       href="${appServer}/admin/blog/save?returnUrl=${appServer}/admin/blog/list/${page.number+1}&blogId=${b.blogId}">修改</a>
                                    <button type="button" class="btn btn-danger" onclick="del('${appServer}/admin/blog/doDelete?returnUrl=${appServer}/admin/blog/list/${page.number+1}&blogId=${b.blogId}')">删除
                                    </button>

                                    <div class="btn-group" role="group">
                                        <button type="button" class="btn btn-primary dropdown-toggle"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            索引
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">生成索引</a></li>
                                            <li><a href="#">删除索引</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- Pager -->
                <ul class="pagination">
                    <c:if test="${page.number+1 != 1}">
                        <li><a href="${appServer}/admin/blog/list/1">&laquo;</a></li>
                    </c:if>
                    <c:forEach items="${pages}" var="c">
                        <c:if test="${c == page.number+1}">
                            <li class="active">
                                <a>${c}</a>
                            </li>
                        </c:if>
                        <c:if test="${c != page.number+1}">
                            <li>
                                <a href="${appServer}/admin/blog/list/${c}">${c}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${page.number+1 != page.totalPages}">
                        <li><a href="${appServer}/admin/blog/list/${page.totalPages}">&raquo;</a></li>
                    </c:if>

                </ul>
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
