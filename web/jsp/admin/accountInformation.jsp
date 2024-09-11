<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Information</title>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
    <div class="container mt-5">
        <h2>Account Information</h2>

        <!-- Display error message if any -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <!-- Display account details -->
        <c:if test="${not empty account}">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Account Details</h5>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><strong>Email:</strong> ${account.email}</li>
                        <li class="list-group-item"><strong>Full Name:</strong> ${account.fullName}</li>
                        <li class="list-group-item"><strong>Phone Number:</strong> ${account.phoneNumber}</li>
                        <li class="list-group-item"><strong>Role:</strong> ${account.role}</li>
                        <li class="list-group-item"><strong>Address:</strong> ${account.address}</li>
                        <li class="list-group-item"><strong>Gender:</strong> ${account.gender}</li>
                        <li class="list-group-item"><strong>Birthday:</strong> ${account.birthday}</li>
                        <li class="list-group-item"><strong>Hourly Rate:</strong> ${account.hourlyRate}</li>
                    </ul>
                </div>
            </div>
        </c:if>

        <!-- Link to return to previous page -->
        <a href="<c:url value='/admin/index.htm'/>" class="btn btn-primary mt-3">Back to Dashboard</a>
    </div>

    <script src="<c:url value='/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>
