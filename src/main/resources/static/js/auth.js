$(document).ready(function () {
    $('#loginForm').submit(function (event) {
        event.preventDefault();
        const email = $('#email').val();
        const password = $('#password').val();

        $.ajax({
            url: '/auth/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ email: email, password: password }),
            success: function (response) {
                localStorage.setItem("jwt", response.jwt);
                window.location.href = '/';
            },
            error: function (error) {
                alert('Your email or password does not match.');
            }
        });
    });

    $('#registerForm').submit(function (event) {
        event.preventDefault();
        const data = {
            username: $('#username').val(),
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            gender: $('input[name="gender"]:checked').val()
        };

        $.ajax({
            url: '/auth/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                window.location.href = '/login';
            },
            error: function () {
                alert('An error occurred when registering.');
            }
        });
    });

});