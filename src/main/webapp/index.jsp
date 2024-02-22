<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>User Registration</title>
</head>
<body>
<h1>User Registration</h1>
<form action="/addUser" method="post">
  <div>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
  </div>
  <div>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
  </div>
  <div>
    <input type="submit" value="Add User">
  </div>
</form>
<a href="/viewUser">View User</a>
</body>
</html>