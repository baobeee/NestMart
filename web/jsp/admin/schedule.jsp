<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="../../admin/static/img/icons/icon-48x48.png" />
        <link href="https://unpkg.com/feather-icons@latest/dist/feather.css" rel="stylesheet">

        <link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.css">

        <title>NestMart - Accounts</title>

        <link rel="stylesheet" href="../assets/admin/css/app.css" />
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
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
                margin-top: -22px;
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
                vertical-align: middle; /* Center-align text vertically in table cells */
            }
        </style>
    </head>
    <body>
        <c:if test="${param.sessionExpired}">
            <div style="color: red;">
                Your session has expired. Please log in again.
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    // Hiển thị lỗi trong modal
                    document.getElementById('errorAlert').textContent = "${error}";
                    document.getElementById('errorAlert').style.display = 'block';
                    $('#createScheduleModal').modal('show'); // Mở lại modal khi có lỗi
                });
            </script>
        </c:if>

        <div class="wrapper">
            <ul class="sidebar-nav">
                <a class="sidebar-brand" href="index.html">
                    <span class="align-middle">Nestmart</span>
                </a>
                <li class="sidebar-header">
                    Pages
                </li>

                 <li class="sidebar-item">
                            <a class="sidebar-link" href="account.htm" >
                                <i class="align-middle me-2" data-feather="users"></i> <span class="align-middle">Account</span>

                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="products.htm">
                                <i class="align-middle" data-feather="box"></i> <span class="align-middle">Product</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="brand.htm">
                                <i class="align-middle" data-feather="bold"></i> <span class="align-middle">Brand</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="categories.htm">
                                <i class="align-middle" data-feather="list"></i> <span class="align-middle">Category</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="categoryDetail.htm">
                                <i class="align-middle" data-feather="clipboard"></i> <span class="align-middle">Category Detail</span>
                            </a>
                        </li>

                        <li class="sidebar-item ">
                            <a class="sidebar-link" href="discount.htm">
                                <i class="align-middle" data-feather="check-circle"></i> <span class="align-middle">Discount</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="offers.htm">
                                <i class="align-middle" data-feather="percent"></i> <span class="align-middle">Offers</span>
                            </a>
                        </li>

                        <li class="sidebar-item active">
                            <a class="sidebar-link" href="schedule.htm">
                                <i class="align-middle" data-feather="calendar"></i> <span class="align-middle">Schedule</span>
                            </a>
                        </li>



                        <li class="sidebar-item">
                            <a class="sidebar-link" href="inventory.htm">
                                <i class="align-middle" data-feather="package"></i> <span class="align-middle">Inventory</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="viewFeedbackAd.htm">
                                <i class="align-middle" data-feather="feather"></i> <span class="align-middle">Feedback</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="salary.htm">
                                <i class="align-middle" data-feather="user-check"></i> <span class="align-middle">Salary</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a class="sidebar-link" href="notifications.htm">
                                <i class="align-middle" data-feather="navigation"></i> <span class="align-middle">Notification</span>
                            </a>
                        </li>
            </ul>

            <div class="main">
                <nav class="navbar navbar-expand navbar-light navbar-bg">
                    <a class="sidebar-toggle js-sidebar-toggle">
                        <i class="hamburger align-self-center"></i>
                    </a>
                    <div class="navbar-collapse collapse">
                        <ul class="navbar-nav navbar-align">
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
                <main class="content">
                    <div class="container mt-4">
                        <!-- Search and Buttons -->
                        <div class="search-container">

                            <div class="icon-container">
                                <button type="button" class="btn btn-add" data-toggle="modal" data-target="#createScheduleModal">
                                    <i data-feather="plus"></i>
                                </button>
                            </div>
                        </div>

                        <table class="table table-hover my-0" id="scheduleTable">
                            <thead>
                                <tr>
                                    <th>Week Start Date</th>
                                    <th>Week End Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="schedule" items="${weekSchedules}">
                                    <tr>
                                <input type="hidden" name="weekScheduleID" value="${schedule.weekScheduleID}">
                                <td>${schedule.weekStartDate}</td>
                                <td>${schedule.weekEndDate}</td>
                                <td class="table-actions">
                                    <a href="../admin/scheduleDetails.htm?weekScheduleID=${schedule.weekScheduleID}" class="btn btn-view" title="View Details">
                                        <i data-feather="eye"></i>
                                    </a>

                                    <form action="../admin/weekScheduleDelete.htm" method="GET" style="display:inline;">
                                        <input type="hidden" name="weekScheduleID" value="${schedule.weekScheduleID}">
                                        <button type="submit" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this schedule?');" title="Delete">
                                            <i data-feather="trash-2"></i>
                                        </button>
                                    </form>

                                </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty weekSchedules}">
                                <tr>
                                    <td colspan="4">No week schedule found.</td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center mt-3">
                            <c:if test="${totalPages > 1}">
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item <c:if test='${currentPage == i}'>active</c:if>">
                                        <a class="page-link" href="?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </nav>

                </main>

                <!--                tạo tuần làm việc-->
                <div class="modal fade" id="createScheduleModal" tabindex="-1" role="dialog" aria-labelledby="createScheduleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="createScheduleModalLabel">Create Week Schedule</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="../admin/weekScheduleCreate.htm" method="POST">
                                    <div class="alert alert-danger" id="errorAlert" style="display:none;"></div>
                                    <div class="form-group">
                                        <label for="weekStartDate">Week Start Date</label>
                                        <input type="date" class="form-control" id="weekStartDate" name="weekStartDate" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="weekEndDate">Week End Date</label>
                                        <input type="date" class="form-control" id="weekEndDate" name="weekEndDate" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Assign Shift -->
                <div class="modal fade" id="assignShiftModal" tabindex="-1" role="dialog" aria-labelledby="assignShiftModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="assignShiftModalLabel">Assign Shift to Employee</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form id="assignShiftForm" action="../admin/assignShift.htm" method="POST">
                                    <!-- Select Week -->
                                    <div class="form-group">
                                        <label for="weekScheduleID">Select Week Schedule</label>
                                        <select class="form-control" id="weekScheduleID" name="weekScheduleID" required>
                                            <c:forEach var="schedule" items="${weekSchedules}">
                                                <option value="${schedule.weekScheduleID}">${schedule.weekStartDate} - ${schedule.weekEndDate}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Select Employee -->
                                    <div class="form-group">
                                        <label for="employeeID">Select Employee</label>
                                        <select class="form-control" id="employeeID" name="employeeID" required>
                                            <c:forEach var="employee" items="${employees}">
                                                <option value="${employee.employeeID}">${employee.fullName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Select Day of Week -->
                                    <div class="form-group">
                                        <label for="dayOfWeekID">Select Day</label>
                                        <select class="form-control" id="dayOfWeekID" name="dayOfWeekID" required>
                                            <c:forEach var="day" items="${daysOfWeek}">
                                                <option value="${day.dayOfWeekID}">${day.dayName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Select Shift -->
                                    <div class="form-group">
                                        <label for="shiftID">Select Shift</label>
                                        <select class="form-control" id="shiftID" name="shiftID" required>
                                            <c:forEach var="shift" items="${shifts}">
                                                <option value="${shift.shiftID}">${shift.shiftName} (${shift.startTime} - ${shift.endTime})</option>
                                            </c:forEach>
                                        </select>
                                    </div>


                                    <!-- Optional Fields for Overtime and Notes -->
                                    <div class="form-group">
                                        <label for="overtime">Overtime (hours)</label>
                                        <input type="number" class="form-control" id="overtime" name="overtime" min="0" step="0.5" placeholder="Enter overtime hours (optional)">
                                    </div>

                                    <div class="form-group">
                                        <label for="note">Notes</label>
                                        <textarea class="form-control" id="note" name="note" rows="3" placeholder="Enter any additional notes (optional)"></textarea>
                                    </div>

                                    <!-- Submit Button -->
                                    <button type="submit" class="btn btn-primary">Assign Shift</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.2/feather.min.js"></script>

            <script src="../asset/admin/js/app.js"></script>
            <script>
                                            feather.replace();
            </script>
            <script>
                function openAssignShiftModal(weekDetailsID) {
                    $('#weekDetailsID').val(weekDetailsID);
                    $('#assignShiftModal').modal('show');
                }
            </script><script>
                function openAssignShiftModal(weekDetailsID) {
                    // Gán giá trị weekDetailsID vào input ẩn (nếu cần)
                    document.getElementById('weekDetailsID').value = weekDetailsID;

                    // Hiển thị modal phân ca
                    var assignShiftModal = new bootstrap.Modal(document.getElementById('assignShiftModal'));
                    assignShiftModal.show();
                }
            </script>
    </body>
</html>
