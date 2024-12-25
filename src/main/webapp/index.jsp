<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>개인정보 검출 프로그램</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <h1>개인정보 검출 프로그램</h1>
</header>

<section id="main">
    <h2>홈 화면</h2>
    <p>이 프로그램은 텍스트에서 개인정보를 자동으로 검출하는 도구입니다. 텍스트를 입력하여 개인정보가 포함되어 있는지 확인할 수 있습니다.</p>

    <form action="/detect" method="POST">
        <label for="inputText">검사할 텍스트:</label><br>
        <textarea id="inputText" name="inputText" rows="4" cols="50" placeholder="텍스트를 입력하세요..."></textarea><br><br>
        <input type="submit" value="검사하기">
    </form>
</section>

<footer>
    <p>&copy; 2024 개인정보 검출 프로그램 | All rights reserved.</p>
</footer>
</body>
</html>