function loadPosts(url, token) {
    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`, // Thêm token vào header
            'Content-Type': 'application/json'  // Nếu cần gửi dữ liệu dưới dạng JSON
        }
    })
        .then(response => response.json())
        .then(data => {
            const postContainer = document.getElementById('postContainer');
            postContainer.innerHTML = ''; // Xóa các post cũ trước khi thêm post mới
            // Kiểm tra nếu có bài đăng
            if (data.responseCode === 1000 && Array.isArray(data.result)) {
                data.result.forEach(post => {
                    // Gọi hàm tạo HTML cho mỗi bài đăng
                    const postElement = createPostHtml(post);
                    postContainer.insertAdjacentHTML('beforeend', postElement);
                });
            } else {
                console.error('Không có bài đăng hoặc lỗi API.');
            }
        })
        .catch(error => console.error('Error fetching posts:', error));
}

function createPostHtml(post) {
    return `
            <div class="card d-flex flex-row p-2 card shadow rounded-3 mb-4">
                <style>
                    /* Nút chung */
                    button {
                        background: none;
                        border: none;
                        padding: 8px;
                        cursor: pointer;
                        display: inline-flex;
                        align-items: center;
                        justify-content: center;
                    }
            
                    .already {
                        fill: red;
                    }
            
                    /* Nút yêu thích */
                    .heart-button:hover .heart-icon {
                        fill: red;
                    }
            
                    .heart-icon {
                        width: 24px;
                        height: 24px;
                        fill: currentColor;
                    }
            
                    /* Nút trò chuyện */
                    .chat-button:hover .chat-icon {
                        fill: cyan;
                    }
            
                    .chat-icon {
                        width: 24px;
                        height: 24px;
                        fill: currentColor;
                    }
            
                    /* Hover màu chữ theo kiểu Tailwind */
                    .hover\\:text-red-400:hover {
                        color: #f87171; /* Màu đỏ */
                    }
            
                    .hover\\:text-cyan-400:hover {
                        color: #22d3ee; /* Màu xanh cyan */
                    }
                </style>
                <div class="d-flex ">
                    <img src="${post.user.avatarUrl || 'https://upload.wikimedia.org/wikipedia/vi/7/7d/Bliss.png'}" alt="avatar" class="rounded-circle border border-secondary" style="width: 2.5rem; height: 2.5rem; margin: 0.5rem;">
                </div>
                <div class="d-flex flex-column w-100">
                    <div class="d-flex align-items-center gap-2">
                        <a class="font-weight-bold">
                            ${post.user.firstName} ${post.user.lastName}
                        </a>
                        <span class="text-secondary">@${post.user.username}</span>
                        <span>·</span>
                        <span class="text-secondary">${new Date(post.createdAt).toLocaleDateString()}</span>
                    </div>
                    <div class="min-h-12">
                        <p class="text-break">${post.caption}</p>
                    </div>
                    ${post.imageUrl ? `<img src="${post.imageUrl}" class="img-fluid rounded border w-100" alt="post image" onclick="event.stopPropagation()">` : ''}
                    ${post.videoUrl ? `<video class="img-fluid rounded border w-100" controls onclick="event.stopPropagation()">
                                        <source src="${post.videoUrl}">
                                        </video>` : ''}
                    <div class="d-flex justify-content-between mt-2">
                        <section class="d-flex gap-4">
                            <!-- Like Button -->
                            <div class="d-flex align-items-center">
                                <button
                                  title="Like this post"
                                  class="heart-button hover:text-red-400"
                                  type="button"
                                  tabindex="0"
                                >
                                  <svg
                                    class="heart-icon"
                                    aria-hidden="true"
                                    viewBox="0 0 24 24"
                                    focusable="false"
                                  >
                                    <path
                                      d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54z"
                                    ></path>
                                  </svg>
                                </button>
                                <button type="button" class="btn btn-link p-0 text-danger" onclick="likePost()">
                                    <span class="hover-underline cursor-pointer" onclick="handleOpenUserLiked()">${post.likedCount}</span>
                                </button>
                            </div>

                            <!-- Comment button -->
                            <div class="d-flex align-items-center">
                                <button
                                  class="chat-button hover:text-cyan-400"
                                  type="button"
                                  tabindex="0"
                                >
                                  <svg
                                    class="chat-icon"
                                    aria-hidden="true"
                                    viewBox="0 0 24 24"
                                    focusable="false"
                                  >
                                    <path
                                      d="M20 2H4c-1.1 0-2 .9-2 2v18l4-4h14c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2"
                                    ></path>
                                  </svg>
                                </button>
                                <button type="button" class="btn btn-link p-0 text-info" onclick="handleOpenComment()">
                                    <span class="hover-underline cursor-pointer" onclick="handleOpenComment()">${post.commentCount}</span>
                                </button>
                            </div>
                        </section>
                        <button type="button" class="btn btn-link p-0 text-info">
                            <i class="bi bi-share"></i>
                        </button>
                    </div>
                </div>
            </div>`;
}

// Tải posts khi trang được load
// document.addEventListener('DOMContentLoaded', loadPosts);