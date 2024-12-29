<%@ page import="com.froggy.piidetection.common.dto.DetectionDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>검사 결과</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detect-result.css">
</head>
<body>
<header>
    <h1>검사 결과</h1>
</header>

<section id="result">
    <%
        List<DetectionDto> results = (List<DetectionDto>) request.getAttribute("results");

        if (results != null && !results.isEmpty()) {
            for (DetectionDto result : results) {
    %>
    <div class="result-item">
        <h3>검출 항목: <%= result.getName() %></h3>
        <p><strong>검출 갯수:</strong> <%= result.getCount() %></p>

        <p><strong>검출된 내용:</strong></p>
        <div class="detected-items">
            <%
                // detectedItems 리스트 출력
                List<String> detectedItems = result.getDetectedItems();
                if (detectedItems != null && !detectedItems.isEmpty()) {
                    for (String item : detectedItems) {
            %>
            <div class="detected-item">
                <span>🔍</span>
                <span><%= item %></span>
            </div>
            <%
                }
            } else {
            %>
            <div class="detected-item">
                <span>⚠️</span>
                <span>No detected items.</span>
            </div>
            <%
                }
            %>
        </div>
        <hr>
    </div>
    <%
        }
    } else {
    %>
    <p>No detection results found.</p>
    <%
        }
    %>

    <a href="/">홈으로 돌아가기</a>
</section>

<footer>
    <p>&copy; 2024 개인정보 검출 프로그램 | All rights reserved.</p>
</footer>

</body>
</html>
