<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Chi Tiết Lịch Làm Việc</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <div class="container">
        <h2>Sửa Chi Tiết Lịch Làm Việc</h2>
        <form action="${pageContext.request.contextPath}/admin/updateScheduleDetail.htm" method="post">
            <input type="hidden" name="id" value="${detail.weekDetailID}">
            <label for="dayName">Ngày:</label>
            <input type="text" id="dayName" name="dayName" value="${detail.dayName}" readonly>
            <label for="shiftName">Ca:</label>
            <input type="text" id="shiftName" name="shiftName" value="${detail.shiftName}" readonly>
            <label for="employeeName">Nhân Viên:</label>
            <input type="text" id="employeeName" name="employeeName" value="${detail.employeeName}" readonly>
            <label for="overtimeHours">Giờ Làm Thêm:</label>
            <input type="text" id="overtimeHours" name="overtimeHours" value="${detail.overtimeHours}">
            <label for="status">Trạng Thái:</label>
            <input type="text" id="status" name="status" value="${detail.status}">
            <label for="notes">Ghi Chú:</label>
            <textarea id="notes" name="notes">${detail.notes}</textarea>
            <button type="submit" class="btn btn-edit">Cập Nhật</button>
            <a href="${pageContext.request.contextPath}/admin/scheduleDetails.htm" class="btn btn-back">Quay lại</a>
        </form>
    </div>
</body>
</html>
