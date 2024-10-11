<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
    <head>
        <meta charset="UTF-8">

        <title>Client Chat - Contact</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            let lastMessageId = 0;
            let processedMessageIds = new Set();

            function fetchMessages() {
                console.log("Fetching messages, lastMessageId:", lastMessageId);
                $.ajax({
                    url: '../client/pollMessages.htm',
                    type: 'GET',
                    data: {lastMessageId: lastMessageId},
                    dataType: 'xml',
                    success: function (response) {
                        console.log("Received XML response:", new XMLSerializer().serializeToString(response));

                        if (!response || !response.documentElement) {
                            console.error("Invalid XML response: empty or missing document element");
                            return;
                        }

                        const $response = $(response);
                        const $messages = $response.find('message');

                        if ($messages.length === 0) {
                            console.log("No messages found in the response");
                            return;
                        }

                        $messages.each(function () {
                            const $message = $(this);
                            const messageText = $message.find('message').text().trim();
                            const messageIdText = $message.find('supportID').text().trim();
                            const status = $message.find('status').text().trim();

                            const messageId = parseInt(messageIdText, 10);
                            const isFromEmployee = $message.find('employeeID').text().toLowerCase() !== 'null';
                            let timestamp = $message.find('sendDate').text().trim();

                            console.log("Processing message:", {messageId, messageText, status, isFromEmployee});

                            // Check if the message ID is valid and not already processed
                            if (!messageIdText || isNaN(messageId) || processedMessageIds.has(messageId)) {
                                return;
                            }

                            processedMessageIds.add(messageId);

                            if (!timestamp) {
                                timestamp = "Unknown time";
                            } else {
                                const date = new Date(timestamp);
                                timestamp = date.toLocaleString();
                            }

                            addMessageToChat(messageText, isFromEmployee, timestamp, messageId, status);
                            lastMessageId = Math.max(lastMessageId, messageId);

                            // Check if the conversation is closed
                            if (status === 'Closed' && messageText === 'Complete conversation by employee') {
                                console.log("Conversation closed message received");
                                handleClosedConversation();
                            }
                        });

                        scrollToBottom();
                    },
                    error: function (xhr, status, error) {
                        console.error("Error fetching messages:", error);
                    }
                });
            }
function handleClosedConversation() {
    console.log("Handling closed conversation");
    $('#chat-form textarea').prop('disabled', true);
    $('#send-button').prop('disabled', true);

    // Check if the closed message is already displayed
    if (!document.querySelector('.message.system')) {
        // Display a message to the user only if it's not already there
        const chatMessages = document.querySelector('.chat-messages');
        const closedMessage = document.createElement('div');
        closedMessage.className = 'message system';
        closedMessage.textContent = 'Complete conversation by employee';
        chatMessages.appendChild(closedMessage);
    }

    // Stop polling for new messages
    clearInterval(pollingInterval);
}
            
            function isMessageAlreadyDisplayed(messageId) {
                return document.querySelector(`.message[data-message-id="${messageId}"]`) !== null;
            }

            // Modify the addMessageToChat function to prevent duplicate messages
            function addMessageToChat(message, isFromEmployee, timestamp, messageId, status) {
                console.log("Adding message to chat:", {message, isFromEmployee, timestamp, messageId, status});

                // Check if the message already exists
                if (isMessageAlreadyDisplayed(messageId)) {
                    console.log("Message already displayed, skipping:", message);
                    return;
                }

                const chatMessages = document.querySelector('.chat-messages');
                const messageElement = document.createElement('div');
                messageElement.className = isFromEmployee ? 'message employee' : 'message client';
                messageElement.setAttribute('data-message-id', messageId);

                const messageContent = document.createElement('p');
                messageContent.textContent = message;

                const timestampElement = document.createElement('span');
                timestampElement.className = 'timestamp';
                timestampElement.textContent = timestamp;

                messageElement.appendChild(messageContent);
                messageElement.appendChild(timestampElement);

                chatMessages.appendChild(messageElement);
                scrollToBottom();

                // If this is the "Complete conversation by employee" message, handle it only once
                if (isFromEmployee && message === 'Complete conversation by employee' && status === 'Closed') {
                    console.log("Calling handleClosedConversation from addMessageToChat");
                    handleClosedConversation();
                }
            }
            
            $(document).ready(function () {
                startPolling();
            });
            
            function scrollToBottom() {
                const chatMessages = document.querySelector('.chat-messages');
                chatMessages.scrollTop = chatMessages.scrollHeight;
                console.log("Scrolled to bottom");
            }
            function sendMessage() {
                const messageInput = document.querySelector('#chat-form textarea');
                const message = messageInput.value.trim();

                if (message) {
                    console.log("Sending message:", message);
                    $.ajax({
                        url: '../client/sendMessage.htm',
                        type: 'POST',
                        data: {message: message},
                        dataType: 'xml',
                        success: function (response) {
                            const status = $(response).find('status').text();
                            if (status === 'success') {
                                const newMessageId = parseInt($(response).find('messageId').text());
                                const timestamp = $(response).find('timestamp').text();

                                // Don't add the message here, let fetchMessages handle it
                                messageInput.value = '';

                                if (newMessageId > lastMessageId) {
                                    lastMessageId = newMessageId;
                                    console.log("Updated lastMessageId after sending:", lastMessageId);
                                }

                                fetchMessages(); // Fetch new messages immediately
                            } else {
                                console.error("Error sending message:", $(response).find('message').text());
                            }
                            $('#message-badge').text('0');

                        },
                        error: function (xhr, status, error) {
                            console.error("Error sending message:", error);
                        }
                    });
                }
            }

            function updateUnreadMessageCount() {
                $.ajax({
                    url: '../client/unreadMessageCount.htm',
                    type: 'GET',
                    success: function (response) {
                        $('#message-badge').text(response);
                    },
                    error: function (xhr, status, error) {
                        console.error("Error fetching unread message count:", error);
                    }
                });
            }
            let pollingInterval;

            function startPolling() {
                console.log("Starting polling");
                fetchMessages(); // Fetch messages immediately when starting
                updateUnreadMessageCount(); // Update unread message count immediately
                pollingInterval = setInterval(function () {
                    fetchMessages();
                    updateUnreadMessageCount();
                }, 2000); // Poll every 2 seconds
            }
            window.onload = function () {
                console.log("Window loaded");
                const messages = document.querySelectorAll('.chat-messages .message');
                if (messages.length > 0) {
                    const lastMessage = messages[messages.length - 1];
                    const messageId = lastMessage.getAttribute('data-message-id');
                    lastMessageId = messageId ? parseInt(messageId) : 0;
                    console.log("Initialized lastMessageId:", lastMessageId);
                }

                startPolling();

                document.getElementById('send-button').addEventListener('click', function (event) {
                    event.preventDefault();
                    sendMessage();
                });

                document.querySelector('#chat-form textarea').addEventListener('keypress', function (event) {
                    if (event.key === 'Enter' && !event.shiftKey) {
                        event.preventDefault();
                        sendMessage();
                    }
                });

                scrollToBottom();
            };
        </script>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f0f0f0;
            }

            /* Chat Widget Button Styles */
            .chat-widget {
                position: fixed;
                bottom: 20px;
                right: 20px;
                background-color: #ee4d2d;
                color: white;
                padding: 8px 12px;
                border-radius: 4px;
                display: flex;
                align-items: center;
                cursor: pointer;
                z-index: 1000;
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
                font-size: 14px;
            }

            .chat-widget .chat-icon {
                display: flex;
                align-items: center;
            }

            .chat-widget .badge {
                background-color: white;
                color: #ee4d2d;
                border-radius: 50%;
                padding: 2px 6px;
                font-size: 12px;
                font-weight: bold;
                margin-left: 8px;
            }

            /* Chat Box Styles */
            .chat-box {
                position: fixed;
                bottom: 80px;
                right: 20px;
                width: 320px;
                height: 480px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                display: none;
                z-index: 1001;
                transition: all 0.3s ease;
                overflow: hidden;
            }

            .chat-header {
                background-color: #ee4d2d;
                color: white;
                padding: 12px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .chat-header h2 {
                margin: 0;
                font-size: 16px;
            }

            .chat-messages {
                height: calc(100% - 120px);
                overflow-y: auto;
                padding: 12px;
                display: flex;
                flex-direction: column;
            }

            .message {
                max-width: 80%;
                margin-bottom: 12px;
                padding: 8px 12px;
                border-radius: 18px;
                position: relative;
                clear: both;
            }

            .message p {
                margin: 0 0 5px 0;
            }

            .message.client {
                align-self: flex-end;
                background-color: #ee4d2d;
                color: white;
                border-bottom-right-radius: 4px;
            }

            .message.employee {
                align-self: flex-start;
                background-color: #f1f0f0;
                color: #333;
                border-bottom-left-radius: 4px;
            }

            .timestamp {
                font-size: 10px;
                opacity: 0.7;
                margin-top: 4px;
                display: block;
            }

            .message.client .timestamp {
                text-align: right;
                color: rgba(255, 255, 255, 0.7);
            }

            .message.employee .timestamp {
                text-align: left;
                color: #888;
            }

            .close-btn {
                background: transparent;
                border: none;
                color: white;
                cursor: pointer;
                font-size: 20px;
            }

            #chat-form {
                display: flex;
                padding: 12px;
                border-top: 1px solid #eee;
                background-color: #fff;
            }

            #chat-form textarea {
                flex-grow: 1;
                border: 1px solid #ddd;
                border-radius: 20px;
                padding: 8px 12px;
                resize: none;
                height: 36px;
                font-family: inherit;
            }

            #chat-form button {
                background-color: #ee4d2d;
                color: white;
                border: none;
                border-radius: 50%;
                width: 36px;
                height: 36px;
                margin-left: 8px;
                cursor: pointer;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            #chat-form button:hover {
                background-color: #d73f1d;
            }

            /* Scrollbar Styles */
            .chat-messages::-webkit-scrollbar {
                width: 6px;
            }

            .chat-messages::-webkit-scrollbar-track {
                background: #f1f1f1;
            }

            .chat-messages::-webkit-scrollbar-thumb {
                background: #888;
                border-radius: 3px;
            }

            .chat-messages::-webkit-scrollbar-thumb:hover {
                background: #555;
            }
            .message.seen {
                opacity: 0.8; /* Ví dụ: làm mờ tin nhắn đã xem */
            }
            .message.system {
                background-color: #f0f0f0;
                color: #666;
                text-align: center;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                font-style: italic;
            }

        </style>
    </head>
    <body>
        <!-- Chat Widget Button -->
        <div id="chat-widget" class="chat-widget" onclick="toggleChat()">
            <div class="chat-icon">
                <i class="fas fa-comments"></i>
                <span>Chat</span>
                <span id="message-badge" class="badge">0</span>
            </div>
        </div>

        <!-- Chat Box Container -->
        <div id="chat-box" class="chat-box">
            <div class="chat-header">
                <h2>Contact Support</h2>
                <button class="close-btn" onclick="toggleChat()">&times;</button>
            </div>
            <div class="chat-messages">
                <c:forEach var="message" items="${messages}">
                    <div class="${message.employeeID == null ? 'message client' : 'message employee'}" data-message-id="${message.supportID}">
                        <p>${message.message}</p>
                        <span class="timestamp">${message.sendDate}</span>
                    </div>
                </c:forEach>
            </div>
            <div id="chat-form">
                <textarea placeholder="Type your message..." required></textarea>
                <button id="send-button" type="button"><i class="fas fa-paper-plane"></i></button>
            </div>
        </div>


        <script>
            function toggleChat() {
                const chatBox = document.getElementById('chat-box');
                if (chatBox.style.display === 'none' || chatBox.style.display === '') {
                    chatBox.style.display = 'block';
                    // Mark messages as seen when opening chat
                } else {
                    chatBox.style.display = 'none';
                }
            }
        </script>
        <jsp:include page="livechat.jsp" />
    </body>
</html>
