<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link href='<c:url value="/css/login.css"/>' rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        let login = {
            url: '',
            init: function (url) {
                this.url = url
                $('#btn_login').click(function (event) {
                    event.preventDefault();

                    let id = $('#memberId').val();
                    let pw = $('#memberPassword').val();
                    if (id === "") {
                        alert("아이디를 입력해주세요.");
                        $('#memberId').focus();
                        return;
                    }
                    if (pw === "") {
                        alert("비밀번호를 입력해주세요.");
                        $('#memberPassword').focus();
                        return;
                    }
                    login.send(id, pw);
                })
            },
            send: function (id, pw) {
                $.ajax({
                    url: this.url,
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        memberId: id,
                        memberPw: pw
                    }),
                    success: function (response) {
                        if (response.response) {
                            window.location.href = '<c:url value="/seekgu"/>';
                        } else if (response.statusCode && response.message) {
                            alert("Error: " + response.message);
                        }
                    },
                    error: function () {
                        console.error('로그인 실패:', error);
                    }
                })
            }
        }
        $(function(){
            login.init('<c:url value="/member/loginImpl"/>');
        });
    </script>
</head>
<body>
    <div class="background">
        <div class="login-wrapper">
            <h2>Login</h2>
            <form id="login-form">
                <input type="text" id="memberId" name="memberId" placeholder="Id">
                <input type="password" id="memberPassword" name="memberPassword" placeholder="Password">
                <input id="btn_login" type="submit" value="Login">
            </form>
            <br>
            <div class="login-to-signup">
                <a href='<c:url value="/member/signup"/>'>or Be Our SeekGoo</a>
            </div>
        </div>
    </div>
</body>
</html>