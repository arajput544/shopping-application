<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CS3220 Finals</title>
</head>
<body>
	<p><a href="AddItem" style="text-decoration:none;">Add Item</a></p>
	<table border='1' cellspacing='0'>
		<tr>
			<th>Item</th>
			<th>Store</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${ shopping_list }" var="list_item">
			<tr>
				<td>${ list_item.getItem_name() }</td>
				<td>${ list_item.getStore_name() }</td>
				<td><a href="DeleteItem?id=${ list_item.getId() }" style="text-decoration:none;">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>