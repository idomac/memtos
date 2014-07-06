<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="memfn" uri="http://github.com/idomac/tags/memtos-functions" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

    <form:form method="post" commandName="app">
        <form:hidden path="id"/>

        <div class="form-group">
            <form:label path="name">应用名称：</form:label>
            <form:input path="name"/>
        </div>

        <div class="form-group">
            <form:label path="app_key">应用KEY：</form:label>
            <form:input path="app_key"/>
        </div>

        <div class="form-group">
            <form:label path="app_secret">应用安全码：</form:label>
            <form:input path="app_secret"/>
        </div>

        <form:button>${op}</form:button>

    </form:form>

</body>
</html>