<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Get Latest Log Lines</title>
</head>

<body>
<h1>Get Latest Log Lines</h1>
<ul>
    <form action="log-collection" method="GET">
        File name: <input type="text" name="filename" /><br />
        Number of entries: <input type="text" name="entries" /><br />
        Filter: <input type="text" name="keyword" /><input type="submit" />
    </form>
</ul>

</body>
</html>
