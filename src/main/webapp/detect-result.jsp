<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>검사 결과</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <h1>검사 결과</h1>
</header>

<section id="result">
    <h2>검출된 개인정보</h2>
    <p><%= request.getAttribute("result") %></p>
    <a href="/">홈으로 돌아가기</a>
</section>

<footer>
    <p>&copy; 2024 개인정보 검출 프로그램 | All rights reserved.</p>
</footer>
</body>
</html>