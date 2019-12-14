<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String imageName = (String) request.getSession().getAttribute("imageName");
    Integer imageId = (Integer) request.getSession().getAttribute("imageId");
%>
<html>
<head>
    <title>comment</title>
</head>
<script type="text/javascript"></script>
<body>
<form action="/LogoutServlet" method="post">
    <td>
        <input type="submit" value="退出登录"></td>
</form>
<tr>
    <th ><%=imageName%>
    </th>
    <th name = "imageId">
        <%=imageId%>
    </th>
</tr>
<images id="image" controls preload="auto" width="400px" height="300px">
    <img src="${src}" alt="" type="image/jpg">
</images>

<table width="600" border="1" cellpadding="0">
    <tr>
        <th>ID</th>
        <th>评论内容</th>
    </tr>
    <c:forEach var="U" items="${comments}">
        <tr>
            <td><input type="hidden" value="${U.userName}" name="username">${U.userName}</td>
            <td><input type="hidden" value="${U.comment}" name="comment">${U.comment}</td>
        </tr>
    </c:forEach>
    <div align="right">

        <a href="comment.jsp?imageId=${imageId}&page=1">首页</a>
        <a href="comment.jsp?imageId=${imageId}&page=${prePage}">上一页</a>
        <a href="comment.jsp?imageId=${imageId}&page=${nextPage}">下一页</a>
        <a href="comment.jsp?imageId=${imageId}&page=${totalPage}">尾页</a>

        第${curPage}页/共${totalPage}页
    </div>
</table>
<form action="${pageContext.request.contextPath}/AddCommentServlet" method="post">
    <table align="center">
        <tr>
        <tr>
        <input  type="hidden" value="<%=imageId%>" name="imageId" />
            <td>
                评论
            </td>
            <td>
                <input type="text" name="comment1"/>
            </td>
        </tr>
        <tr>
            <input type="submit" name="Submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
