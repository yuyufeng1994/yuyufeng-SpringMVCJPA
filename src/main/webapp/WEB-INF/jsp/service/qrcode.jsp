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
    <title>二维码生成--服务--心得站点</title>
    <%@include file="/WEB-INF/jsp/include/head.jsp" %>
</head>
<body>
<!-- Navigation -->
<%@include file="/WEB-INF/jsp/include/nav.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-12">
            <h1 class="my-4">二维码生成服务</h1>
            <table class="table table-bordered">
                <tr>
                    <th>请求地址</th>
                    <td>${appServer}/service/doQrcode</td>
                </tr>
                <tr>
                    <th>参数</th>
                    <th>说明</th>
                </tr>
                <tr>
                    <td>content</td>
                    <td>要生成二维码的内容</td>
                </tr>
                <tr>
                    <th>返回</th>
                    <td>图片流</td>
                </tr>
            </table>

            <h3>在线生成</h3>
            <form id="my-form">
                <div class="form-group">
                    <label for="for-content">内容</label>
                    <textarea class="form-control" id="for-content" placeholder="输入内容" required></textarea>
                </div>
                <button type="button" onclick="createQrcode()" class="btn btn-default">立即生成</button>
                <hr/>
                <div class="form-group">
                    <label for="img-result">结果</label><br/>
                    <a title="点击下载" href="javascript:downloadQrcode()"><img id="img-result" src="" alt="待生成" class="img-thumbnail"></a>
                    <p class="help-block">如需要扫描二维码后跳转,请输入"http://xxx.com" 格式(注:没在微信认证的地址,无法使用微信扫描跳转后访问)</p>
                </div>
            </form>

        </div>

    </div>
    <!-- /.row -->
    <hr>
</div>
<!-- /.container -->
<%@include file="/WEB-INF/jsp/include/foot.jsp" %>

</body>
<script>
    function createQrcode() {
        var content = $("#for-content").val().trim();
        if (content == null || content == '' || content.size <= 0) {
            alert("请输入内容!")
            return;
        }
        $("#img-result").attr('src', '${appServer}/service/doQrcode?content=' + content);
    }

    function downloadQrcode() {
        var content = $("#for-content").val().trim();
        if (content == null || content == '' || content.size <= 0) {
            alert("请先输入内容!")
            return;
        }
        var toHref = '${appServer}/service/doQrcodeDownload?content=' + content;
        console.log(toHref)
        window.open(toHref,'_blank');
    }
</script>
</html>
