<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form method="post">
  <table border="1" cellpadding="5">
    <caption><h2>Add New Food</h2></caption>
    <tr>
      <th>Food Id</th>
      <td>
        <input type="text" name="food_id" id="food_id" size="45">
      </td>
    </tr>
    <tr>
      <th>Employee Name</th>
      <td><input type="text" name="food_name" id="food_name" size="45"></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" value="Save"/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
