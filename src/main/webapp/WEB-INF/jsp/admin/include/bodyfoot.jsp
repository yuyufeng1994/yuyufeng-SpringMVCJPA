<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/5/31
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  id="myModal" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">模态框</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <textarea id="myModalContent" class="form-control" rows="15"
                              placeholder=""></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <p>Copyright &copy; 心得站点 2017 </p>
            <p><a href="http://www.miitbeian.gov.cn/">浙ICP备17023003号</a></p>
            <p><img src="${staticServer}/static/image/备案图标.png"> <a
                    href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33048202000193">浙公网安备
                33048202000193号</a></p>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</footer>
<!-- jQuery -->
<script src="${staticServer}/static/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${staticServer}/static/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${staticServer}/static/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<!--<script src="vendor/raphael/raphael.min.js"></script>
<script src="vendor/morrisjs/morris.min.js"></script>-->

<!-- Custom Theme JavaScript -->
<script src="${staticServer}/static/vendor/sbadmin//js/sb-admin-2.js"></script>
