$(document).ready(function () {
    // Hiển thị thông tin người dùng đăng nhập thành công
    const url = window.location.pathname;
    let userId = url.split('/').pop();
    $.ajax({
        type: 'GET',
        url: `/api/users/${userId}`,
        contentType: 'application/json',
        beforeSend: function (xhr) {
            if (localStorage.jwt) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.jwt);
            }
        },
        success: function (data) {
            // Cập nhật thông tin người dùng vào phần tử HTML
            $('#uFirstName').html(data.result.firstName);
            $('#uLastName').html(data.result.lastName);
            $('#uUsername').html('@' + data.result.username);
            $('#uPosts').html(data.result.posts + ' Posts');
            $('#uFollowers').html(data.result.followers + ' Followers');
            $('#uFollowings').html(data.result.followings + ' Followings');
            if (data.result.avatarUrl != null)
                $('#avatar').html(document.getElementById('uAvatar').src = data.result.avatarUrl);
            if (data.result.coverPhotoUrl != null)
                $('#avatar').html(document.getElementById('uCover').src = data.result.coverPhotoUrl);
        },
        // error: function (e) {
        //     var json = e.responseText;
        //     $('#feedback').html(json);
        //     window.location.href = "/login";
        // }
    });
});