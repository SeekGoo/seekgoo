<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.png"/>"/>
    <link href='<c:url value="/css/signup.css"/>' rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        let signup = {
            url: '',
            init: function (url) {
                this.url = url
                $('#btn_signup').click(function (event) {
                    event.preventDefault();

                    let id = $('#memberId').val();
                    let name = $('#memberName').val();
                    let pw = $('#memberPassword').val();
                    let nickName = $('#memberNickName').val();
                    let gender = $("input[name='memberGender']:checked").val();
                    if (id === "") {
                        alert("아이디를 입력해주세요.");
                        $('#memberId').focus();
                        return;
                    }
                    if (name === "") {
                        alert("이름을 입력해주세요.");
                        $('#memberPassword').focus();
                        return;
                    }
                    if (pw === "") {
                        alert("비밀번호를 입력해주세요.");
                        $('#memberPassword').focus();
                        return;
                    }
                    if (nickName === "") {
                        alert("닉네임을 입력해주세요.");
                        $('#memberPassword').focus();
                        return;
                    }
                    if (!gender) {
                        alert("성별을 선택해주세요.");
                        return;
                    }
                    signup.send(id, name, pw, nickName, gender);
                })
            },
            send: function (id, name, pw, nickName, gender) {
                $.ajax({
                    url: this.url,
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        memberName: name,
                        memberId: id,
                        memberPw: pw,
                        memberNickName: nickName,
                        memberGender: gender
                    }),
                    success: function (response) {
                        if (response.response) {
                            window.location.href = '<c:url value="/member/login"/>';
                        } else if (response.statusCode && response.message) {
                            alert("Error: " + response.message);
                        }
                    },
                    error: function () {
                        console.error('회원가입 실패:', error);
                    }
                })
            }
        }
        $(function(){
            signup.init('<c:url value="/member/signupImpl"/>');
        });
    </script>
</head>
<body>
    <div class="background">
        <div class="signup-wrapper">
            <h2>Sign Up</h2>
            <form id="signup-form">
                <input type="text" id="memberId" name="memberId" placeholder="Id">
                <input type="text" id="memberName" name="memberName" placeholder="Name">
                <input type="password" id="memberPassword" name="memberPassword" placeholder="Password">
                <input type="text" id="memberNickName" name="memberNickName" placeholder="Nickname">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="memberGender" id="MALE" value="MALE">
                    <label class="form-check-label" for="MALE">
                        MAN
                    </label>
                    <input class="form-check-input" type="radio" name="memberGender" id="FEMALE" value="FEMALE">
                    <label class="form-check-label" for="FEMALE">
                        WOMAN
                    </label>
                </div>
                <input id="btn_signup" type="submit" value="Sign Up">
            </form>
            <div class="signup-to-home col">
                <a href='<c:url value="/seekgu"/>'>or to Home</a>
            </div>
        </div>
    </div>
</body>
</html>