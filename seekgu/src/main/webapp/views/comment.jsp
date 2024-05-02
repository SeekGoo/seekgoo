<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Shop Homepage - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.ico"/>"/>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<c:url value="/css/comment.css"/>" rel="stylesheet"/>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="<c:url value="/js/scripts.js"/>"></script>
</head>

<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light p-0">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">
            <img src="<c:url value="/assets/logo_black.png"/>" alt="로고 이미지">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-3 mb-2 mb-lg-0 ms-lg-auto">
                <li class="nav-item fs-12"><a class="nav-link active" href="<c:url value="member/login">">Login</a></li>
                <li class="nav-item fs-12"><a class="nav-link active" href="<c:url value="member/signup">">Sign Up</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Header-->
<header class=" main-banner">
    <div class="container px-4 px-lg-5">
        <div class="text-center text-white">
            <img src="<c:url value = "/assets/logo_white.png"/>" style="width: 200px;">
        </div>
    </div>
</header>
<!-- Section-->

<div class="gathering">
    <h4 class="fw-bolder">${seekguBoard.seekguTitle}</h4>
    <p class="fs-14 fw-bolder">작성자 : ${seekguBoard.seekguMemberNickName}</p>

    <div class="divider"></div>
    <span class="badge timer-background text-center fs-6 p-3 mb-2">
            TIMER 09 : 50
        </span>
    <div class="divider"></div>
    <div>
        <p>${seekguBoard.seekguContent}</p>
    </div>
    <div class="divider"></div>
    <div>
        <img src="/assets/logo_black.png" width="300px" alt="이미지 설명">
    </div>
</div>
<c:if test="${not empty seekguBoard.reviewList}">
    <c:forEach var="review" items="${seekguBoard.reviewList}">
        <div class="comment">
            <p class="fs-12">${review.memberNickname}</p>
            <div class="divider"></div>
            <div>
                <p class="fs-14">${review.reviewComment}</p>
            </div>
        </div>
    </c:forEach>
</c:if>

<div class="write-comment">
    <textarea placeholder="댓글을 입력하세요"></textarea>
    <button class="write-comment-btn">댓글 등록</button>
</div>


<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p>
    </div>
</footer>

</body>

</html>