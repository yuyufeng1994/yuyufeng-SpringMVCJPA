<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/6/2
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<!-- Blog Search Well -->
<div class="well">
    <h4>博客搜索</h4>
    <div class="input-group">
        <input type="text" class="form-control">
        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                        </button>
                        </span>
    </div>
    <!-- /.input-group -->
</div>
<!-- Blog Categories Well -->
<div class="well">
    <h4>博客目录</h4>
    <div class="row">

        <div class="col-lg-6">
            <ul class="list-unstyled">
                <c:forEach items="${catalogs}" var="c" varStatus="index">
                <li><a href="${appServer}/blog/list-catalog/${c.catalogId}/1" title="${c.catalogBrief}">${c.catalogName}
                    [${c.blogsSize}]</a></li>
                <c:if test="${(index.count)%4 == 0}">
            </ul>
        </div>
        <div class="col-lg-6">
            <ul class="list-unstyled">
                </c:if>
                </c:forEach>
            </ul>
        </div>

    </div>
    <!-- /.row -->
</div>
<%--http://127.0.0.1/common/stream/decode?content=--%>
<!-- Side Widget Well -->
<div class="well">
    <h4>二维码</h4>
    <img id="decode-img" alt="加载中..." class="img-thumbnail">
    <script>
        $("#decode-img").attr('src', 'http://127.0.0.1/common/stream/decode?content='+window.location.href);
    </script>
</div>

<!-- Side Widget Well -->
<div class="well">
    <h4>友情连接</h4>
    <p><a href="http://www.shenfeichao.top">沈飞超的博客-分享计划</a></p>
    <p><a href="http://www.yuyufeng.top">俞育峰的博客-心得站点</a></p>
</div>

