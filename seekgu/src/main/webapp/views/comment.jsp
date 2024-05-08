<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5597ce572bdb5f34d523fbcb3ecbb32f"></script>
    <title>Shop Homepage - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.png"/>"/>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<c:url value="/css/comment.css"/>" rel="stylesheet"/>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        let timer = {
            leftTimeInSeconds: 0,
            timerInterval: null,
            init:function () {
                var regStartTime = new Date("${seekguBoard.seekguRegDate}");
                var limitTimeInSeconds = ${seekguBoard.seekguLimitTime} * 60;
                var deadlineTimeInSeconds = regStartTime.getTime()/1000 + (limitTimeInSeconds);
                var currentTimeInSeconds = new Date().getTime()/1000;
                timer.leftTimeInSeconds = parseInt(deadlineTimeInSeconds - currentTimeInSeconds);

                timer.updateTimer();
                timer.timerInterval = setInterval(timer.updateTimer, 1000);
            },
            updateTimer:function () {
                var remainingTimeElement = $('#remaining_time');

                if (timer.leftTimeInSeconds <= 0) {
                    clearInterval(timer.timerInterval);
                    remainingTimeElement.html("모집 마감");
                    $('#engage-button').prop('disabled', true);
                    return;
                }

                var minutes = Math.floor(timer.leftTimeInSeconds / 60);
                var seconds = timer.leftTimeInSeconds % 60;

                remainingTimeElement.html(minutes + " : " + seconds);
                timer.leftTimeInSeconds--;
            }
        };
        
        let engage = {
            url: '',
            init:function (url) {
                this.url = url;

                $('#engage-button').click(function () {
                    $.ajax({
                        url: url,
                        method: "POST",
                        success: function (response) {
                            console.log(response);
                            if (response.response) {
                                window.location.href = '<c:url value="/seekgu"/>';
                            } else if (response.statusCode && response.message) {
                                if(response.statusCode == 5000) {
                                    alert(response.message);
                                    window.location.href = '<c:url value="/member/login"/>';
                                }
                            }
                        },
                        error: function () {
                            console.error('참여하기 실패:', error);
                        }
                    })
                })

            }
        }

        let map = {
            init: function (){
                let lat = ${seekguBoard.seekguRestaurantLatitude};
                let lng = ${seekguBoard.seekguRestaurantLongitude};
                var mapContainer = document.getElementById('map-zone'), // 지도를 표시할 div
                    mapOption = {
                        center: new kakao.maps.LatLng(parseFloat(lat), parseFloat(lng)), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };
                var map = new kakao.maps.Map(mapContainer, mapOption);
                var imageSrc = '<c:url value="/assets/marker.png"/>',
                    imageSize = new kakao.maps.Size(80, 80),
                    imageOption = {offset: new kakao.maps.Point(27, 69)};
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
                var markerPosition  = new kakao.maps.LatLng(parseFloat(lat), parseFloat(lng));
                var marker = new kakao.maps.Marker({
                    position: markerPosition,
                    image: markerImage
                });
                marker.setMap(map);

                var iwContent = '<div style="font-size:12px;">${seekguBoard.seekguRestaurantName}</div>',
                    iwPosition = new kakao.maps.LatLng(parseFloat(lat), parseFloat(lng));

// 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    position : iwPosition,
                    content : iwContent
                });

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
                infowindow.open(map, marker);
            }
        }
        $(document).ready(function() {
            timer.init();
            engage.init('<c:url value="/seekgu/participate?seekguIdx="/>${seekguBoard.seekguIdx}');
            map.init();
        });
    </script>
</head>

<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light p-0">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="<c:url value="/seekgu"/> ">
            <img src="<c:url value="/assets/logo_black.png"/>" alt="로고 이미지">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <c:choose>
            <c:when test="${sessionScope.memberId == null}">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-3 mb-2 mb-lg-0 ms-lg-auto">
                        <li class="nav-item fs-12"><a class="nav-link active" href="<c:url value="/member/login"/>">Login</a></li>
                        <li class="nav-item fs-12"><a class="nav-link active" href="<c:url value="/member/signup"/>">Sign Up</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-3 mb-2 mb-lg-0 ms-lg-auto">
                        <li class="nav-item fs-12"><a class="nav-link active" href="#">${sessionScope.memberNickName}</a></li>
                        <li class="nav-item fs-12"><a class="nav-link active" href="<c:url value="/member/logout"/>">Logout</a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
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

<c:if test="${seekguBoard.isRecruiting && seekguBoard.memberIdx != sessionScope.memberId}">
    <button id="engage-button">
        <img src="/assets/logo_white.png" width="50px" alt="이미지_설명">
        <br>
            ${seekguBoard.seekguMemberCount} / ${seekguBoard.seekguMax}<br>
        <span id="remaining_time"></span>
        <p>참여하기</p>
    </button>
</c:if>
<!-- Section-->

<div class="gathering">
    <h4 class="fw-bolder">${seekguBoard.seekguTitle}</h4>
    <p class="fs-14 fw-bolder">작성자 : ${seekguBoard.seekguMemberNickName}</p>
    <div>
        <p style="font-size: 0.8rem">${seekguBoard.seekguContent}</p>
    </div>
    <div class="divider"></div>
    <div id="map-zone">
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

<c:if test="${!seekguBoard.isRecruiting}">
    <div class="write-comment">
        <textarea placeholder="댓글을 입력하세요"></textarea>
        <button class="write-comment-btn">댓글 등록</button>
    </div>
</c:if>


<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p>
    </div>
</footer>

</body>

</html>