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
                        <li><a href="${appServer}/blog/list-catalog/${c.catalogId}/1" title="${c.catalogBrief} ">${c.catalogName} [${c.blogsSize}]</a> </li>
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

<!-- Side Widget Well -->
<div class="well">
    <h4>Side Widget Well</h4>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium
        odit aliquam repellat tempore quos aspernatur vero.</p>
</div>

