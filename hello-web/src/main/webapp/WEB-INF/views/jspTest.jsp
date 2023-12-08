<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Jsp | Test</title>
  <style>
    table {
      border: 1px solid #000;
      border-collapse: collapse;
      margin: 20px 10px;
    }

    th, td {
      border: 1px solid #000;
      padding: 5px;
    }
  </style>
</head>
<body>
<h1>Test</h1>

<h3>names</h3>
<ul></ul>

<h3>items</h3>
<table></table>

<h3>map</h3>
<table>
  <tbody>
  <tr>
    <th>이름</th>
    <td>
      <input type="text" />
    </td>
  </tr>
  <tr>
    <th>나이</th>
    <td>
      <input type="number" />
    </td>
  </tr>
  <tr>
    <th>결혼여부</th>
    <td>
      <input type="checkbox" />
    </td>
  </tr>
  </tbody>
</table>


<h2>숫자</h2>
<ul>
  <li></li><%-- 123.46 --%>
  <li></li><%-- 123.456 --%>
  <li></li><%-- 123.45600 --%>
  <li></li><%-- 3,000,000 --%>
  <li></li><%-- ₩3,000,000 --%>
  <li></li><%-- 15% --%>
</ul>

<h2>날짜/시각</h2>
<ul>
  <li></li><%-- 2023년07월24일 --%>
  <li></li><%-- 2023/12/07 18:00:52.335 --%>
</ul>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</body>
</html>