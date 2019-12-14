<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath(); //获取项目根目录
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>" rel="external nofollow">
    <title>上传图片</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
    <td>
        <input type="submit" value="退出登录"></td>
</form>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="panel-heading" align="center">
            <h1 class="sub-header h3">图片上传</h1>
        </div>
        <hr>
        <%
            String errorInfo = (String) request.getAttribute("msg");         // 获取错误属性
            if (errorInfo != null) {
        %>
        <script type="text/javascript" language="javascript">
            alert("<%=errorInfo%>");
        </script>
        <%
            }
        %>
        <form class="form-horizontal" id="upload" method="post" action="${pageContext.request.contextPath}/uploadServlet.doPost"
              enctype="multipart/form-data">
            <div class="form-group" align="center">
                <tr>
                    <td class="td1">上传图片</td>
                    <td><input type="file" id="photo" name="upload">
                        <input type="submit" value="上传"></td>
                </tr>
            </div>
        </form>
    </div>
</div>
<table width="600" border="1" cellpadding="0" align="center">
    <tr>
        <th>ID</th>
        <th>图片名称</th>
        <th>上传用户</th>
    <c:forEach var="U" items="${image}">
        <form action="${pageContext.request.contextPath}/CommentServlet" method="Get">
            <tr>
                <td><input type="hidden" value="${U.imageId}" name="imageId"/>${U.imageId}</td>
                <td>${U.imageName}</td>
                <td>${U.userName}</td>
                <td><input type="submit" value="详情"/></td>
            </tr>
        </form>
    </c:forEach>
    <div align="center">

        <a href="show.jsp?page=1">首页</a>
        <a href="show.jsp?page=${prePage}">上一页</a>
        <a href="show.jsp?page=${nextPage}">下一页</a>
        <a href="show.jsp?page=${totalPage}">尾页</a>

        第${curPage}页/共${totalPage}页
    </div>
</table>

</body>
</html>