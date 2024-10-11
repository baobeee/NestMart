<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
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

        <title>NestMart - Weekly Work Shedule Details</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/css/app.css" />
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
                vertical-align: middle;
            }
            .btn-add {
                background-color: #f1f1f1; /* Matches the background of other pages */
                border: none;
                color: #333;
                width: 50px; /* Standard size for the button */
                height: 50px;
                border-radius: 50%; /* Circular button */
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                font-size: 24px; /* Size of the icon */
                transition: background-color 0.3s, box-shadow 0.3s;
            }

            .btn-add:hover {
                background-color: #e0e0e0; /* Hover effect similar to other pages */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .btn-add i {
                font-size: 24px;
                color: #333;
            }

        </style>
    </head>
    <body>
        <c:if test="${param.sessionExpired}">
            <div style="color: red;">
                Your session has expired. Please log in again.
            </div>
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
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/accountInformation.htm">
                                        <i class="fa fa-user"></i> Account Information
                                    </a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/changePassword.htm">
                                        <i class="fa fa-user"></i> Change Password
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.htm">
                                        <i class="align-middle me-1" data-feather="log-out"></i> Log out
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <main class="content">
                    <div class="container mt-4">
                        <button class="btn btn-add" type="button" data-toggle="modal" data-target="#assignShiftModal">
                            <i data-feather="plus"></i>
                        </button>

                        <h2>Weekly Work Schedule Details: <i><strong>
                                    <fmt:formatDate value="${weekSchedule.weekStartDate}" pattern="yyyy/MM/dd" />
                                    - 
                                    <fmt:formatDate value="${weekSchedule.weekEndDate}" pattern="yyyy/MM/dd" />
                                </strong></i></h2>

                        <c:choose>
                            <c:when test="${hasData}">
                                <div class="table-hover">
                                    <table class="table table-bordered ">
                                    <thead>
                                        <tr>
                                            <th>Day Name</th>
                                            <th>Shift Name</th>
                                            <th>Employee Details</th>
                                            <th>Overtime Hours</th>
                                            <th>Status</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="entry" items="${weekDetailsGroupedByDayAndShift}">
                                            <c:set var="currentDay" value="${entry.key}" />
                                            <c:set var="shifts" value="${entry.value}" />
                                            <c:set var="isFirstShift" value="true" />

                                            <c:forEach var="shift" items="${shifts}">
                                                <c:set var="currentShift" value="${shift.key}" />
                                                <c:set var="shiftDetails" value="${shift.value}" />

                                                <tr>
                                                    <td><c:if test="${isFirstShift}">${currentDay}</c:if></td>
                                                    <td>${currentShift}</td>
                                                    <td>
                                                        <c:forEach var="details" items="${shiftDetails}" varStatus="status">
                                                            ${employeeMap[details.employeeID]} (${roleMap[details.employeeID]})
                                                            <c:if test="${!status.last}"><br></c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <c:forEach var="details" items="${shiftDetails}" varStatus="status">
                                                            ${details.overtimeHours}
                                                            <c:if test="${!status.last}"><br></c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <c:forEach var="details" items="${shiftDetails}" varStatus="status">
                                                            ${details.status}
                                                            <c:if test="${!status.last}"><br></c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <c:forEach var="details" items="${shiftDetails}">
                                                            <form method="get" action="${pageContext.request.contextPath}/admin/editWeekDetail.htm">
                                                                <input type="hidden" name="weekScheduleID" value="${details.weekScheduleID}">
                                                                <input type="hidden" name="weekDetailID" value="${details.weekDetailID}">
                                                                <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                                                            </form>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <c:forEach var="details" items="${shiftDetails}">
                                                            <form method="post" action="${pageContext.request.contextPath}/admin/deleteShift.htm" 
                                                                  onsubmit="return confirm('Are you sure you want to delete this shift detail?');">
                                                                <input type="hidden" name="shiftID" value="${details.shiftID}">
                                                                <input type="hidden" name="weekScheduleID" value="${weekSchedule.weekScheduleID}">
                                                                <input type="hidden" name="weekDetailID" value="${details.weekDetailID}">
                                                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                                            </form>
                                                        </c:forEach>
                                                    </td>
                                                </tr>
                                                <c:set var="isFirstShift" value="false" />
                                            </c:forEach>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                </div>
                                
                            </c:when>
                            <c:otherwise>
                                <p>No schedule details available for this week.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!-- Modal assign shift-->
                    <div class="modal fade" id="assignShiftModal" tabindex="-1" role="dialog" aria-labelledby="assignShiftModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="assignShiftModalLabel">Assign Shift</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="assignShiftForm" method="post" action="${pageContext.request.contextPath}/admin/assignShift.htm">
                                        <input type="hidden" name="weekScheduleID" value="${weekSchedule.weekScheduleID}">

                                        <!-- Select Employee -->
                                        <div class="form-group">
                                            <label for="employeeSelect">Select Employee</label>
                                            <select class="form-control" id="employeeSelect" name="employeeID">
                                                <c:forEach var="employee" items="${accountsList}">
                                                    <option value="${employee.accountID}">
                                                        ${employee.fullName} (${employee.role == 2 ? 'Employee' : 'Shipper'})
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <!-- Select Day -->
                                        <div class="form-group">
                                            <label for="daySelect">Select Day</label>
                                            <select class="form-control" id="daySelect" name="dayID" required>
                                                <c:forEach var="day" items="${dayOfWeekList}">
                                                    <option value="${day.dayID}">${day.dayName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <!-- Select Shift -->
                                        <div class="form-group">
                                            <label for="shiftSelect">Select Shift</label>
                                            <select class="form-control" id="shiftSelect" name="shiftID" required>
                                                <c:forEach var="shift" items="${shiftList}">
                                                    <option value="${shift.shiftID}">${shift.shiftName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary" form="assignShiftForm">Assign Shift</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <div class="row text-muted">
                    <div class="col-6 text-start">
                        <p class="mb-0">
                            <a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>Nestmart</strong></a> &copy;
                        </p>
                    </div>
                    <div class="col-6 text-end">
                        <ul class="list-inline">
                            <li class="list-inline-item">
                                <a class="text-muted" href="https://adminkit.io/" target="_blank">Support</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="text-muted" href="https://adminkit.io/" target="_blank">Help Center</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="text-muted" href="https://adminkit.io/" target="_blank">Privacy</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="text-muted" href="https://adminkit.io/" target="_blank">Terms</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
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
            document.addEventListener("DOMContentLoaded", function () {
                let totalHours = document.getElementById('totalHours');
                let hourlyRate = document.getElementById('hourlyRate');
                let totalSalary = document.getElementById('totalSalary');

                function calculateTotalSalary() {
                    let hours = parseFloat(totalHours.value) || 0;
                    let rate = parseFloat(hourlyRate.value) || 0;
                    totalSalary.value = hours * rate;
                }

                totalHours.addEventListener('input', calculateTotalSalary);
                hourlyRate.addEventListener('input', calculateTotalSalary);
            });
        </script>
        <script>
            function submitShiftForm() {
                document.getElementById('shiftForm').submit();
            }
        </script>
        <script>
            // JavaScript to load shift details when edit button is clicked
            document.addEventListener("DOMContentLoaded", function () {
                const editShiftButtons = document.querySelectorAll('.edit-shift-button');

                editShiftButtons.forEach(button => {
                    button.addEventListener('click', function () {
                        const weekDetailID = this.getAttribute('data-week-detail-id');
                        loadShiftDetails(weekDetailID);
                    });
                });

                function loadShiftDetails(weekDetailID) {
                    // Gi? s? có m?t endpoint ?? l?y thông tin ca làm vi?c
                    fetch(`/admin/getShiftDetails?weekDetailID=${weekDetailID}`)
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById('editWeekDetailID').value = data.weekDetailID;
                                document.getElementById('editEmployeeSelect').value = data.employeeID;
                                document.getElementById('editDaySelect').value = data.dayID;
                                document.getElementById('editShiftSelect').value = data.shiftID;
                                document.getElementById('editOvertimeHours').value = data.overtimeHours;
                                document.getElementById('editStatus').value = data.status;
                                document.getElementById('editNotes').value = data.notes;

                                $('#editShiftModal').modal('show'); // M? modal
                            })
                            .catch(error => {
                                console.error('Error fetching shift details:', error);
                            });
                }
            });

        </script>
        <script>
            function openEditModal(weekDetailID, shiftID, overtimeHours, status, notes) {
                document.getElementById('weekDetailID').value = weekDetailID;
                document.getElementById('shiftSelect').value = shiftID;
                document.getElementById('overtimeHours').value = overtimeHours;
                document.getElementById('status').value = status;
                document.getElementById('notes').value = notes;

                $('#editShiftModal').modal('show');
            }

        </script>

    </body>
</html>
