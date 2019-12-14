<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="/registerServlet.do" method="post">
    <table align="center" >
        <tr>
            <td><h1>注册新用户</h1></td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>用户密码</td>
            <td><input type="password" name="password"/></td>

        </tr>
        <%String errorInfo = (String)request.getAttribute("msg");         // 获取错误属性
            if(errorInfo != null)
            {
        %>
        <script type="text/javascript" language="javascript">
            alert("<%=errorInfo%>");
        </script>
        <%
            }
        %>
        <tr>
            <td>
                <input type="submit" name="Submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
