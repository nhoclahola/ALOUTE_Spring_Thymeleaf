$(document).ready(function () {
    // Hiển thị thông tin người dùng đăng nhập thành công
    $.ajax({
        type: 'GET',
        url: '/api/users/fromToken',
        contentType: 'application/json',
        beforeSend: function (xhr) {
            if (localStorage.jwt) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.jwt);
            }
        },
        success: function (data) {
            var json = JSON.stringify(data, null, 4);
            // Cập nhật thông tin người dùng vào phần tử HTML
            $('#firstName').html(data.result.firstName);
            $('#lastName').html(data.result.lastName);
            $('#username').html('@' + data.result.username);
            $('#images').html(document.getElementById('images').src = data.images);
        },
        error: function (e) {
            var json = e.responseText;
            $('#feedback').html(json);
            window.location.href = "/login";
        }
    });
    // Login
    // $('#Login').click(function () {
    //     var email = document.getElementById('email').value;
    //     var password = document.getElementById('password').value;
    //     var basicInfo = JSON.stringify({
    //         email: email,
    //         password: password
    //     });
    //     $.ajax({
    //         type: "POST",
    //         url: "/auth/login",
    //         dataType: "json",
    //         contentType: "application/json; charset=utf-8",
    //         data: basicInfo,
    //         success: function(data) {
    //             localStorage.token = data.token;
    //             window.location.href = "/user/profile";
    //         },
    //         error: function() {
    //             alert("Login failed");
    //         }
    //     })
    // })
    // // Logout
    // $('#Logout').click(function () {
    //     localStorage.clear();
    //     window.location.href = "/login";
    // });
});