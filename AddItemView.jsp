<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CS3220 Finals</title>
</head>
<body>
	<form action="AddItem" method="post">
		<p><b>Item : </b><input type="text" name = "item_name" /></p>
		<p><b>Store : </b>
		<select name = "existing_store">
			<option disabled selected></option>
			<c:forEach items="${ stores }" var="store">
				<option>${ store.getStore_name() }</option>
			</c:forEach>
		</select>
		 or
		 <input type="text" name = "store_name" />
		</p>
		<input type="submit" value="Add">
	</form>
</body>
</html>