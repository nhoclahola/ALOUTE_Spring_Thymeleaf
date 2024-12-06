function loadUserChats(url, token) {
    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Kiểm tra nếu có bài đăng
            if (data.responseCode === 1000 && Array.isArray(data.result) && data.result.length > 0) {
                let currentUser = user;
                data.result.forEach(userChat => {
                    const otherUser = userChat.users.find(user => user.userId !== currentUser.userId);
                    const userChatElement = createUserChatHtml(userChat, otherUser);
                    const userChatContainer = document.getElementById('user-chat-container');
                    userChatContainer.insertAdjacentHTML('beforeend', userChatElement);

                    // Thêm event listener cho từng phần tử vừa tạo
                    const chatDiv = document.getElementById(userChat.chatId);
                    const chatInput = document.getElementById('chat-input');
                    if (chatDiv) {
                        chatDiv.addEventListener('click', () => {
                            // disconnectClient();
                            clearAndAddChatMessages(userChat.chatId);
                            chatInput.style.display = 'flex';
                            subscribeToUserTopic(userChat.chatId);
                        });
                    }
                });
            } else {
                // console.error('Không có bài đăng hoặc lỗi API.');
                // Nếu không có dữ liệu, loại bỏ event listener
                showEndMessage(); // Hiển thị thông báo "This is the end..."
                removeLoadMoreButton();
            }
        })
        .catch(error => console.error('Error fetching posts:', error));
}

function removeLoadMoreButton() {
    const loadMoreButton = document.getElementById('load-more');
    if (loadMoreButton) {
        loadMoreButton.remove();
    }
}

// Gán sự kiện nhấn nút "See more notifications"
function setupLoadMoreButton(url, token) {
    const loadMoreButton = document.getElementById('load-more');
    if (loadMoreButton) {
        loadMoreButton.addEventListener('click', () => {
            currentIndex += 10;
            const newUrl = `${url}?index=${currentIndex}`;
            loadNotifications(newUrl, token);
        });
    }
}

function showEndMessage() {
    const endMessage = document.createElement('div');
    endMessage.id = 'endMessage';
    endMessage.style.textAlign = 'center';
    endMessage.style.padding = '20px';
    endMessage.style.fontSize = '18px';
    endMessage.style.color = '#888';
    endMessage.style.fontWeight = 'bold';
    endMessage.style.borderTop = '1px solid #ccc';
    endMessage.style.marginTop = '20px';

    endMessage.innerHTML = `
        <div>
            <p>There are no more notifications...</p>
        </div>
    `;
    notificationContainer.appendChild(endMessage);
}

function createUserChatHtml(userChat, otherUser) {
    return `
            <div role="button" id="${userChat.chatId}" class="p-2 border rounded mb-2">
                <div class="d-flex align-items-center column-gap-2">
                    <div class="avatar-custom">
                        <img src="${otherUser.avatarUrl ? otherUser.avatarUrl : '/images/unknown_user.jpg'}" alt="avatar">
                    </div>                    
                    <div>
                        <h6 class="mb-0">${otherUser.firstName} ${otherUser.lastName}</h6>
                        <small>@${otherUser.username}</small>
                    </div>
                </div>
            </div>`;
}

function clearAndAddChatMessages(chatId) {
    let token = localStorage.getItem("jwt");
    fetch(`/api/messages/chats/${chatId}?index=0`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Kiểm tra nếu có bài đăng
            if (data.responseCode === 1000 && Array.isArray(data.result) && data.result.length > 0) {
                let currentUser = user;
                let messages = data.result;
                data.result.forEach(userChat => {
                    const chatContainer = document.getElementById('chat-container');
                    if (chatContainer) {
                        // Clear previous chat messages
                        chatContainer.innerHTML = '';

                        // Create chat container div
                        const chatMessagesDiv = document.createElement('div');
                        chatMessagesDiv.classList.add('chat-messages');
                        chatMessagesDiv.id = 'chat-messages';

                        // Iterate over the userChat messages
                        messages.forEach(message => {
                            const messageDiv = document.createElement('div');
                            messageDiv.classList.add('p-2');
                            const isCurrentUser = message.user.userId === currentUser.userId;

                            // Check if the message is from the current user or another user
                            if (isCurrentUser) {
                                // If message is from current user, display as outgoing message
                                messageDiv.classList.add('text-end');
                                messageDiv.innerHTML = `
                                    <div id="${message.messageId}">
                                        <div class="bg-primary text-white rounded p-2 d-inline-block">
                                            <p class="mb-0">${message.content}</p>
                                        </div>
                                        <small class="text-muted d-block">${formatTimestamp(message.timestamp)}</small>
                                    </div>
                                `;
                            } else {
                                // If message is from another user, display as incoming message
                                messageDiv.innerHTML = `
                                <div id="${message.messageId}" class="d-flex">
                                    <div class="avatar-custom-2">
                                        <img src="${message.user.avatarUrl || '/images/unknown_user.jpg'}" alt="avatar" class="rounded-circle me-2">
                                    </div>
                                    <div>
                                        <div class="bg-light rounded p-2">
                                            <p class="mb-0">${message.content}</p>
                                        </div>
                                        <small class="text-muted">${formatTimestamp(message.timestamp)}</small>
                                    </div>
                                </div>
                                `;
                            }

                            // Append message to chat container
                            chatMessagesDiv.appendChild(messageDiv);
                        });

                        // Append chat container to the chatContainer div
                        chatContainer.appendChild(chatMessagesDiv);

                        // Scroll to the bottom of the chat container
                        chatContainer.scrollTop = chatContainer.scrollHeight;
                    }
                });
            }
            else {
                // console.error('Không có bài đăng hoặc lỗi API.');
                // Nếu không có dữ liệu, loại bỏ event listener
                showEndMessage(); // Hiển thị thông báo "This is the end..."
                removeLoadMoreButton();
            }
        })
        .catch(error => console.error('Error fetching posts:', error));
}

// Helper function to format the timestamp to a readable time
function formatTimestamp(timestamp) {
    const date = new Date(timestamp);

    // Lấy ngày, tháng, năm
    const day = String(date.getDate()).padStart(2, '0'); // Đảm bảo có 2 chữ số cho ngày
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Tháng từ 0-11, phải cộng thêm 1
    const year = date.getFullYear();

    // Lấy giờ và phút
    const hours = String(date.getHours()).padStart(2, '0'); // Đảm bảo có 2 chữ số cho giờ
    const minutes = String(date.getMinutes()).padStart(2, '0'); // Đảm bảo có 2 chữ số cho phút

    // Trả về chuỗi định dạng "dd/mm/yyyy hh:mm"
    return `${day}/${month}/${year} ${hours}:${minutes}`;
}