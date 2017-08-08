<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/5/31
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/admin/include/head.jsp" %>
    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }

        .text {
            border: 1px solid #ccc;
            height: 600px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <%@ include file="/WEB-INF/jsp/admin/include/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    <c:if test="${empty blog.blogId}">博客新增</c:if>
                    <c:if test="${!empty blog.blogId}">博客修改</c:if>
                    <small>上次修改时间:<fmt:formatDate value="${blog.updateTime}" type="both"
                                                  pattern="yyyy-MM-dd HH:mm:ss"/></small>
                </h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <form id="form-blog" action="${appServer}/admin/blog/doSave" method="post">

                    <input type="hidden" id="inputId" name="blogId" value="${blog.blogId}">
                    <input type="hidden" id="returnUrl" name="returnUrl" value="${returnUrl}">

                    <div class="form-group">
                        <label for="inputTitle">标题</label>
                        <input type="text" class="form-control" id="inputTitle" placeholder="标题" name="blogTitle"
                               value="${blog.blogTitle}" required>
                        <p class="help-block">请输入博客标题</p>
                    </div>

                    <div class="form-group">
                        <label>正文</label>
                        <div id="toolbar" class="toolbar">
                        </div>
                        <%--<div style="padding: 5px 0; color: #ccc">中间隔离带</div>--%>
                        <div id="editor" class="text">${blog.blogContent}</div>
                        <p class="help-block">请输入博客正文</p>
                        <input type="hidden" value="" name="blogContent" id="blogContent" required>
                    </div>


                    <div class="form-group">
                        <label for="inputBrief">简介</label>
                        <textarea class="form-control" id="inputBrief" placeholder="简介"
                                  name="blogBrief">${blog.blogBrief}</textarea>
                        <p class="help-block">请输入博客简介</p>
                    </div>

                    <div class="form-group">
                        <label for="inputImage">封面图片地址</label>
                        <input type="text" class="form-control" id="inputImage" placeholder="封面图片地址" name="blogImage"
                               value="${blog.blogImage}">
                        <p class="help-block">博客内容中上传的第一张上传图片会修改此项(推荐使用900*300的图片)</p>
                    </div>


                    <div class="checkbox">
                        <c:forEach items="${catalogs}" var="c">
                            <label>
                                <input type="checkbox" value="${c.catalogId}"
                                       name="catalogIds" ${c.checked}> ${c.catalogName}
                            </label>
                            |
                        </c:forEach>
                        <p class="help-block">请选择分类</p>
                    </div>

                    <div class="form-group">
                        <label for="inputStatus">状态</label>
                        <select class="form-control" id="inputStatus" name="blogStatus" required>
                            <c:forEach items="${fnc:getBlogStatusEnumValues()}" var="bse">
                                <option value="${bse.key}">${bse.value}</option>
                            </c:forEach>
                        </select>
                        <script>
                            $("#inputStatus").val('${blog.blogStatus}')
                        </script>
                        <p class="help-block">博客内容中上传的第一张上传图片会修改此项(推荐使用900*300的图片)</p>
                    </div>

                    <button type="submit" class="btn btn-default" onclick="submitBlog()">提交</button>

                </form>
                <hr/>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
<%@ include file="/WEB-INF/jsp/admin/include/bodyfoot.jsp" %>


</body>
<script type="text/javascript" src="${staticServer}/static/vendor/wang/wangEditor.min.js"></script>
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


    //富文本编辑器
    var E = window.wangEditor
    var editor = new E('#toolbar', '#editor')
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
//        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ]
    // 配置服务器端地址
    editor.customConfig.uploadImgServer = '${appServer}/admin/file/upload'
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 请求发送之前
        },
        success: function (xhr, editor, result) {
            // 上传成功之后
            // result 是服务器端返回的结果
//            console.log(result.data[0])
            if ($("#inputImage").val().length == 0) {
                $("#inputImage").val(result.data[0]);
            }
        },
        fail: function (xhr, editor, result) {
            // 上传失败之后
            // result 是服务器端返回的结果
            console.log(result)
        },
        error: function (xhr, editor) {
            // 请求发生错误
        },
        timeout: function (xhr, editor) {
            // 请求超时
        }
    }

    editor.create()


    //表单提交
    function submitBlog() {
        $("#blogContent").val(editor.txt.html())
        if ($("#inputTitle").val().length == 0) {
            return false;
        }

        $("#form-blog").submit();

    }

</script>
</html>
