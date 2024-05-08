<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5597ce572bdb5f34d523fbcb3ecbb32f"></script>
    <script
            src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
    <title>식구를 구해보세요</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.png"/>"/>
    <link href= "<c:url value="/css/seekgooboardwrite.css"/> " rel="stylesheet" />
    <script>
        let seekgooboardwrite = {
            init: function (){
                var mapContainer = document.getElementById('include-map'), // 지도를 표시할 div
                    mapOption = {
                        center: new kakao.maps.LatLng(37.5449352, 127.0566045), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };
                var map = new kakao.maps.Map(mapContainer, mapOption);

                $('#search-map-btn').click(() => {
                    this.search();
                });
                $('#send_form_btn').click(() =>{
                    this.send();
                })
            },
            search: function () {
                let keyword = $('#search-map-keyword').val();
                $.ajax({
                    url: '<c:url value="/api/map/search"/>?keyword=' + keyword,
                    success:function(data){
                        console.log(data)
                        seekgooboardwrite.makeList(data);
                    }
                })
            },
            makeList: function(data){
                const box = document.getElementById('search-result');
                box.innerHTML = '';
                data.forEach(item =>{
                    const div = document.createElement('div');
                    const p1 = document.createElement('p');
                    const p2 = document.createElement('p');
                    const input1 = document.createElement('input')
                    const input2 = document.createElement('input')
                    const hr = document.createElement('hr');

                    p1.setAttribute('class', 'restaurant');
                    input1.setAttribute('class', 'latitude');
                    input2.setAttribute('class', 'longitude');


                    p1.style.fontSize = '12px';
                    p1.style.fontWeight = 'bold';
                    p2.style.fontSize = '10px';
                    input1.type = 'hidden';
                    input2.type = 'hidden';

                    p1.textContent = item.placeName;
                    p2.textContent = item.roadAddressName;
                    input1.value = item.x
                    input2.value = item.y;

                    div.appendChild(p1);
                    div.appendChild(p2);
                    div.appendChild(input1);
                    div.appendChild(input2);
                    div.appendChild(hr);

                    div.addEventListener('click', () =>{
                        const children = div.children;
                        const name = p1.textContent;
                        const lat = input1.value;
                        const lng = input2.value;
                        Array.from(children).forEach(child => {
                            if(child.className === 'restaurant'){
                                $('#seekguRestaurantName').val(name)
                            } else if(child.className === 'latitude'){
                                $('#seekguRestaurantLatitude').val(lng)
                            } else if(child.className === 'longitude'){
                                $('#seekguRestaurantLongitude').val(lat)
                            }
                        });
                        var mapContainer = document.getElementById('include-map'), // 지도를 표시할 div
                            mapOption = {
                                center: new kakao.maps.LatLng(parseFloat(lng), parseFloat(lat)), // 지도의 중심좌표
                                level: 2 // 지도의 확대 레벨
                            };
                        var map = new kakao.maps.Map(mapContainer, mapOption);
                        var imageSrc = '<c:url value="/assets/marker.png"/>',
                            imageSize = new kakao.maps.Size(80, 80),
                            imageOption = {offset: new kakao.maps.Point(27, 69)};
                        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
                        var markerPosition  = new kakao.maps.LatLng(parseFloat(lng), parseFloat(lat));
                        var marker = new kakao.maps.Marker({
                            position: markerPosition,
                            image: markerImage
                        });
                        marker.setMap(map);
                    })

                    box.appendChild(div);
                });
            },
            send: function () {
                let title = $('#seekguTitle').val();
                let content = $('#seekguContent').val();
                let minNum = $('#seekguMin').val();
                let maxNum = $('#seekguMax').val();
                let limit = $('#seekguLimitTime').val();
                let meal = $('#seekguMealTime').val()
                let name = $('#seekguRestaurantName').val();
                let lat = $('#seekguRestaurantLatitude').val();
                let lng = $('#seekguRestaurantLongitude').val();
                let idx = $('#memberIdx').val();

                $.ajax({
                    url: '<c:url value="/seekgu/writeImpl"/> ',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        memberIdx: idx,
                        seekguTitle: title,
                        seekguContent: content,
                        seekguMin: minNum,
                        seekguMax: maxNum,
                        seekguLimitTime: limit,
                        seekguMealTime: meal,
                        seekguRestaurantName: name,
                        seekguRestaurantLatitude: lat,
                        seekguRestaurantLongitude: lng,
                    }),
                    success: function (response) {
                        if (response.response) {
                            window.location.href = '<c:url value="/seekgu"/>';
                        } else if (response.statusCode && response.message) {
                            alert("Error: " + response.message);
                        }
                    },
                    error: function () {
                        console.error('모집 등록 실패:', error);
                    }
                })

            }
        };

        $(function () {
            seekgooboardwrite.init();
        });
    </script>
</head>

<body>
<input type="hidden" value="${sessionScope.memberId}" id="memberIdx">
    <div class="container">
        <div class="left-box">
            <div id="include-map">

            </div>
        </div>
        <div class="right-box">
            <!-- 우측 박스 내용 -->
            <div class="seekgooboardwrite-wrapper">
                <h2>Gather</h2>
                <div id="search-map-form">
                    <input type="text" id="search-map-keyword" name="keyword" placeholder="식당검색">
                    <button id="search-map-btn">search</button>
                    <div id="search-result"></div>
                </div>
                <div id="seekgooboardwrite-form">
                    <input type="text" name="seekguTitle" id="seekguTitle" placeholder="제목을 입력하세요">
                    <textarea name="seekguContent" id="seekguContent" placeholder="어그로를 끌어주세요"></textarea>
                    <input type="text" name="seekguMin" id="seekguMin" placeholder="최소인원: 2 ~ n" style="width: 49%;">
                    <input type="text" name="seekguMax" id="seekguMax" placeholder="최대인원: 2 ~ n" style="width: 49%;">
                    <label for="seekguLimitTime">마감시간:</label>
                    <select name="seekguLimitTime" id="seekguLimitTime">
                        <option value="10">10분</option>
                        <option value="20">20분</option>
                        <option value="30">30분</option>
                        <option value="40">40분</option>
                        <option value="50">50분</option>
                        <option value="60">60분</option>
                    </select>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="seekguMealTime" value="LUNCH" id="seekguMealTime">
                        <label class="form-check-label" for="seekguMealTime">
                            점심
                        </label>
                        <input class="form-check-input" type="radio" name="seekguMealTime" value="DINNER" id="seekguMealTime">
                        <label class="form-check-label" for="seekguMealTime">
                            저녁
                        </label>
                    </div>
                    <input type="hidden" id="seekguRestaurantName" name="seekguRestaurantName">
                    <input type="hidden" id="seekguRestaurantLatitude" name="seekguRestaurantLatitude">
                    <input type="hidden" id="seekguRestaurantLongitude" name="seekguRestaurantLongitude">
                    <button id="send_form_btn">Shoot@@@</button>
                </div>
                <div class="write-to-home col">
                    <a href='<c:url value="/seekgu"/>'>or to Home</a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>