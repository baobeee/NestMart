<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="../../admin/static/img/icons/icon-48x48.png" />
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">
        <title>Employee Support Messages</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <link rel="stylesheet" href="../assets/admin/css/app.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <style>
            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
                width: 100%;
            }

            .search-form {
                display: flex;
                align-items: center;
                width: 100%;
            }

            .search-input {
                flex: 1;
                height: 45px;
                padding: 0 10px;
                border-radius: 20px 0 0 20px;
                border: 1px solid #ced4da;
                border-right: none;
                font-size: 16px;
            }

            .search-button {
                height: 45px;
                width: 65px;
                border-radius: 0 20px 20px 0;
                border: 1px solid #ced4da;
                border-left: none;
                background-color: #f1f1f1;
                color: #333;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                margin-right: 15px;
            }

            .search-button i {
                font-size: 20px;
            }

            .search-button:hover {
                background-color: #e0e0e0;
            }
            .icon-container {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-left: auto;
            }
            .icon-container .btn {
                background-color: #f1f1f1;
                border: none;
                color: #333;
                width: 50px;
                height: 50px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                font-size: 24px;
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .icon-container .btn:hover {
                background-color: #e0e0e0;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .table-actions {
                position: relative;
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 5px;
            }
            .table-actions {
                display: flex;
                gap: 5px;
                justify-content: center;
                align-items: center;
            }
            .table-actions .btn {
                background-color: transparent;
                border: none;
                cursor: pointer;
                font-size: 20.5px;
                padding: 5px;
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .table-actions .btn-update {
                color: #007bff;
            }
            .table-actions .btn-delete {
                color: #dc3545;
            }
            .table-actions .btn:hover {
                background-color: #f0f0f0;
                box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
            }
            .table-actions .btn-update:hover {
                color: #0056b3;
            }
            .table-actions .btn-delete:hover {
                color: #c82333;
            }
            tr {
                position: relative;
            }
            .table td {
                vertical-align: middle;
            }
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
            }

            .pagination .page-item .page-link {
                color: #282c3c;
                background-color: #fff;
                border: 1px solid #282c3c;
                padding: 8px 16px;
                margin: 0 4px;
                border-radius: 20px;
                transition: all 0.3s ease;
            }

            .pagination .page-item.active .page-link {
                color: #fff;
                background-color: #282c3c;
                border-color: #282c3c;
            }

            .pagination .page-item .page-link:hover {
                color: #fff;
                background-color: #282c3c;
                border-color: #282c3c;
            }

            .pagination .page-item.disabled .page-link {
                color: #6c757d;
                background-color: #fff;
                border-color: #dee2e6;
                cursor: not-allowed;
            }
            .container {
                display: flex;
                height: 100vh;

            }
        
            .sidebar {
                width: 250px;
                background-color: #fff;
                height: 100vh;
                overflow-y: auto;
                border-right: 1px solid #e0e0e0;
                box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            }

            .main-content {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                height: 100vh;
                overflow: hidden;
            }


            .chat-list {
                list-style-type: none;
                padding: 0;
                margin: 0;
            }

            .chat-item {
                padding: 15px;
                border-bottom: 1px solid #e0e0e0;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .chat-item:hover {
                background-color: #f5f5f5;
            }

            .chat-item.active {
                background-color: #e1f5fe;
                border-left: 4px solid #2196f3;
            }



            .chat-header {
                background-color: #2196f3;
                color: white;
                padding: 15px 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }

            .chat-header h2 {
                margin: 0;
                font-size: 18px;
            }

            #complete-btn {
                background-color: #4caf50;
                color: white;
                border: none;
                padding: 8px 15px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            #complete-btn:hover {
                background-color: #45a049;
            }

            #chat-messages {
                flex-grow: 1;
                overflow-y: auto;
                padding: 20px;
                display: flex;
                flex-direction: column;
                gap: 15px;
            }

            .message {
                max-width: 70%;
                padding: 10px 15px;
                border-radius: 18px;
                box-shadow: 0 1px 2px rgba(0,0,0,0.1);
                line-height: 1.4;
            }

            .customer {
                align-self: flex-start;
                background-color: #f1f0f0;
            }

            .employee {
                align-self: flex-end;
                background-color: #dcf8c6;
            }

            .timestamp {
                font-size: 0.75em;
                color: #888;
                margin-top: 5px;
                text-align: right;
            }

            #chat-form {
                display: flex;
                padding: 15px;
                background-color: #fff;
                border-top: 1px solid #e0e0e0;
            }

            #message-input {
                flex-grow: 1;
                border: 1px solid #ddd;
                border-radius: 20px;
                padding: 10px 15px;
                font-size: 14px;
                resize: none;
                height: 40px;
                transition: border-color 0.3s;
            }

            #message-input:focus {
                outline: none;
                border-color: #2196f3;
            }

            #send-button {
                background-color: #2196f3;
                color: white;
                border: none;
                border-radius: 50%;
                width: 40px;
                height: 40px;
                margin-left: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            #send-button:hover {
                background-color: #1e88e5;
            }

            .customer-info {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .message-time {
                font-size: 0.8em;
                color: #888;
            }

            #no-chat-selected {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%;
                font-size: 16px;
                color: #888;
            }

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <div class="wrapper">
            <nav id="sidebar" class="sidebar js-sidebar">
                <div class="sidebar-content js-simplebar">
                    <a class="sidebar-brand" href="index.html">
                        <span class="align-middle">Nestmart</span>
                    </a>

                    <ul class="sidebar-nav">
                        <li class="sidebar-header">
                            Pages
                        </li>

                        <li class="sidebar-item">
                    <a class="sidebar-link" href="index.htm">
                        <i class="align-middle me-2" data-feather="user"></i> <span class="align-middle">Account</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="order.htm">
                        <i class="align-middle me-2" data-feather="shopping-cart"></i> <span class="align-middle">Orders</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="returnRequests.htm">
                        <i class="align-middle me-2" data-feather="rotate-ccw"></i> <span class="align-middle">Return Request</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="viewFeedbackEmp.htm">
                        <i class="align-middle me-2" data-feather="star"></i> <span class="align-middle">Feedback</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" href="viewNotifications.htm">
                        <i class="align-middle me-2" data-feather="bell"></i> <span class="align-middle">Notification</span>
                    </a>
                </li>
                <li class="sidebar-item active">
                    <a class="sidebar-link" href="supportmessage.htm">
                        <i class="align-middle me-2" data-feather="message-square"></i> <span class="align-middle">Support Message</span>
                    </a>
                </li>

                    </ul>

                    <div class="sidebar-cta">
                        <div class="sidebar-cta-content">
                            <strong class="d-inline-block mb-2">Upgrade to Pro</strong>
                            <div class="mb-3 text-sm">
                                Are you looking for more components? Check out our premium version.
                            </div>
                            <div class="d-grid">
                                <a href="upgrade-to-pro.html" class="btn btn-primary">Upgrade to Pro</a>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>

            <div class="main">
                <nav class="navbar navbar-expand navbar-light navbar-bg">
                    <a class="sidebar-toggle js-sidebar-toggle">
                        <i class="hamburger align-self-center"></i>
                    </a>

                    <div class="navbar-collapse collapse">
                        <ul class="navbar-nav navbar-align">
                            <!-- Các mục khác của navbar... -->

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                                    <span class="text-dark">${sessionScope.email}</span>
                                </a>

                                <div class="dropdown-menu dropdown-menu-end">
                                    <a class="dropdown-item" href="../account/accountInformation.htm">
                                        <i class="fa fa-user"></i> Account Information
                                    </a>
                                    <a class="dropdown-item" href="../account/changePassword.htm">
                                        <i class="fa fa-user"></i> Change Password
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="../logout.htm">
                                        <i class="align-middle me-1" data-feather="log-out"></i> Log out
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>

                <main class="content" style="display: flex;  margin-top: -3.0rem !important;
                margin-left: -3.5rem !important;">
                        <div class="sidebar">
                            <ul class="chat-list">
                                <c:choose>
                                    <c:when test="${empty customers}">
                                        <li>No customers with messages.</li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="customer" items="${customers}">
                                            <li class="chat-item ${customer.employeeID != null ? 'right' : 'left'}" data-customer-id="${customer.customerID}" data-employee-id="${customer.employeeID}">
                                                <div class="customer-info">
                                                    <strong><c:out value="${customer.fullName}" /></strong> (<c:out value="${customer.phoneNumber}" />)
                                                </div>
                                                <div class="message-time">
                                                    <fmt:formatDate value="${customer.sendDate}" pattern="HH:mm, dd/MM/yyyy" />
                                                </div>
                                                <c:if test="${customer.employeeID != null}">
                                                    <div class="processing-info">This customer is being handled by employee #<c:out value="${customer.employeeID}" />.</div>
                                                </c:if>
                                            </li>

                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>


                            </ul>
                        </div>
                        <div class="main-content">
                            <div class="chat-header">
                                <h2>Support Chat</h2>
                                <button id="complete-btn" style="display: none;">Complete Conversation</button>
                            </div>

                            <div id="chat-messages">
                                <div id="no-chat-selected" style="text-align: center; padding: 20px;">
                                    Please select a customer to view their messages.
                                </div>

                                <c:forEach var="message" items="${messages}">
                                    <div class="message ${message.employeeID == null ? 'customer' : 'employee'}">
                                        <div class="message-content"><c:out value="${message.message}" /></div>
                                        <div class="timestamp">
                                            <fmt:formatDate value="${message.sendDate}" pattern="HH:mm, dd/MM/yyyy" />
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div id="chat-form">
                                <textarea id="message-input" placeholder="Type your message..." required></textarea>
                                <button id="send-button" type="button"><i class="fas fa-paper-plane"></i></button>
                            </div>
                        </div>



                </main>

            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js"></script> <!-- Feather Icons JS -->
        <script>
            feather.replace();
        </script>
        <script src="admin/static/js/app.js"></script>
    </body>
    <script>
     document.addEventListener('DOMContentLoaded', function () {
         let currentCustomerID = null;
         document.querySelectorAll('.chat-item').forEach(item => {
             item.addEventListener('click', function () {
                 document.querySelectorAll('.chat-item').forEach(el => el.classList.remove('active'));
                 this.classList.add('active');

                 currentCustomerID = this.getAttribute('data-customer-id');
                 if (currentCustomerID) {
                     $('#chat-messages').show();
                     loadMessages(currentCustomerID);
                     const completeBtn = document.querySelector('#complete-btn');
                     if (completeBtn) {
                         completeBtn.style.display = 'block';
                     }

                     startPollingMessages(currentCustomerID);
                 }
             });
         });

         function startPollingMessages(customerID) {
             if (window.pollingInterval) {
                 clearInterval(window.pollingInterval);
             }

             window.pollingInterval = setInterval(function () {
                 loadMessages(customerID);
             }, 5000);
         }
         function loadMessages(customerID) {
             if (!customerID) {
                 console.error("Customer ID is missing or invalid");
                 return;
             }

             $.ajax({
                 url: '../employee/getCustomerMessages.htm',
                 type: 'GET',
                 dataType: 'xml',
                 contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                 data: {customerID: customerID},
                 success: function (data) {
                     console.log("Raw XML response:", new XMLSerializer().serializeToString(data));
                     var messages = $(data).find('message');
                     $('#chat-messages').empty();
                     console.log("Number of messages found:", messages.length);
                     messages.each(function () {
                         var parsedMessage = {
                             supportID: $(this).find('supportID').text(),
                             customerID: $(this).find('customerID').text(),
                             employeeID: $(this).find('employeeID').text(),
                             content: $(this).find('messageContent').text(),
                             date: $(this).find('sendDate').text()
                         };
                         console.log("Parsed message:", parsedMessage);

                         if (parsedMessage.content) {
                             addMessageToChat(
                                     parsedMessage.content,
                                     parsedMessage.employeeID !== 'null' && parsedMessage.employeeID !== '',
                                     parsedMessage.date
                                     );
                         } else {
                             console.warn("Empty message content for supportID:", parsedMessage.supportID);
                         }
                     });

                     scrollChatToBottom();
                 },
                 error: function (xhr, status, error) {
                     console.error('Error fetching messages:', status, error);
                     console.error('Response Text:', xhr.responseText);
                 }
             });
         }
         function scrollChatToBottom() {
             const chatMessages = document.getElementById('chat-messages');
             chatMessages.scrollTop = chatMessages.scrollHeight;
         }
         function checkMessageVisibility() {
             const messages = $('#chat-messages .message');
             console.log("Total messages:", messages.length);
             messages.each(function (index) {
                 const $this = $(this);
                 console.log(`Message ${index + 1}:`, {
                     visible: $this.is(':visible'),
                     display: $this.css('display'),
                     height: $this.height(),
                     text: $this.text()
                 });
             });
         }
         function completeConversation() {
             if (!currentCustomerID) {
                 console.log("No customer selected");
                 return;
             }

             if (confirm("Are you sure you want to complete and delete this conversation?")) {
                 console.log(`Attempting to complete conversation for customer ID: ${currentCustomerID}`);
                 fetch(`../employee/completeConversation.htm`, {
                     method: 'POST',
                     headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                     body: new URLSearchParams({
                         customerID: currentCustomerID
                     })
                 })
                         .then(response => {
                             console.log(`Server response status: ${response.status}`);
                             console.log(`Server response status text: ${response.statusText}`);
                             return response.text().then(text => {
                                 console.log(`Server response body: ${text}`);
                                 if (response.ok) {
                                     return text;
                                 } else {
                                     throw new Error(`Server responded with status: ${response.status}, body: ${text}`);
                                 }
                             });
                         })
                         .then(result => {
                             console.log(`Processing server response: ${result}`);
                             if (result === 'success') {
                                 alert("Conversation completed and deleted successfully.");
                                 const chatItem = document.querySelector(`.chat-item[data-customer-id="${currentCustomerID}"]`);
                                 if (chatItem) {
                                     chatItem.remove();
                                 } else {
                                     console.warn(`Chat item for customer ID ${currentCustomerID} not found in the DOM`);
                                 }
                                 document.getElementById('chat-messages').innerHTML = '';
                                 document.getElementById('complete-btn').style.display = 'none';
                                 currentCustomerID = null;
                             } else {
                                 throw new Error(`Server returned unexpected result: ${result}`);
                             }
                         })
                         .catch(error => {
                             console.error("Detailed error in completing conversation:", error);
                             alert(`Failed to complete conversation. Error: ${error.message}`);
                         });
             }
         }

// Make sure this line is in your DOMContentLoaded event listener
         document.getElementById('complete-btn').addEventListener('click', completeConversation);

         function addMessageToChat(message, isFromEmployee = false, timestamp = null) {
             console.log("Adding message to chat:", {message, isFromEmployee, timestamp});

             const chatMessages = $('#chat-messages');
             const messageElement = $('<div>').addClass(isFromEmployee ? 'message employee' : 'message customer');

             try {
                 const messageContent = $('<div>').addClass('message-content').text(message);
                 console.log("Created message content element:", messageContent[0].outerHTML);
                 messageElement.append(messageContent);

                 if (timestamp) {
                     const formattedDate = new Date(timestamp).toLocaleString('en-US', {
                         year: 'numeric',
                         month: '2-digit',
                         day: '2-digit',
                         hour: '2-digit',
                         minute: '2-digit',
                         second: '2-digit'
                     });
                     const timeElement = $('<div>').addClass('timestamp').text(formattedDate);
                     console.log("Created time element:", timeElement[0].outerHTML);
                     messageElement.append(timeElement);
                 }

                 console.log("Final message element before appending:", messageElement[0].outerHTML);
                 chatMessages.append(messageElement);

                 // Check if the message was actually added to the DOM
                 const addedMessage = chatMessages.find(`.message:contains('${message}')`);
                 if (addedMessage.length === 0) {
                     console.error("Message was not successfully added to the DOM");
                 } else {
                     console.log("Message successfully added to the DOM");
                 }
             } catch (error) {
                 console.error("Error in addMessageToChat:", error);
             }

             scrollChatToBottom();
         }

         function sendMessage() {
             const messageInput = document.querySelector('#message-input');
             const message = messageInput.value.trim();

             if (!currentCustomerID) {
                 console.log("No customer selected");
                 return;
             }

             if (message) {
                 console.log("Sending message:", message);

                 fetch(`../employee/sendMessage.htm`, {
                     method: 'POST',
                     headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                     body: new URLSearchParams({
                         customerID: currentCustomerID,
                         message: message
                     })
                 })
                         .then(response => {
                             if (response.ok) {
                                 return response.text();
                             } else {
                                 throw new Error("Error sending message: " + response.statusText);
                             }
                         })
                         .then(result => {
                             if (result === 'success') {
                                 addMessageToChat(message, true, new Date().toISOString());
                                 scrollChatToBottom();
                                 messageInput.value = '';
                                 loadMessages(currentCustomerID);
                             } else {
                                 throw new Error("Failed to send message");
                             }
                         })
                         .catch(error => {
                             console.error("Error sending message:", error);
                             alert("Failed to send message. Please try again.");
                         });
             }
         }

         document.querySelector('#send-button').addEventListener('click', sendMessage);

         document.querySelector('#message-input').addEventListener('keypress', function (e) {
             if (e.key === 'Enter' && !e.shiftKey) {
                 e.preventDefault();
                 sendMessage();
             }
         });
     });


    </script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</html>
