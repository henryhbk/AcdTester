<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<title>Search Adverse Events</title>
</head>
<body>

<div class="container">
<form action="/patient">
<select class="form-select" aria-label="search by drug name">
  <option value="drug"><drug></option>
</select>
    <button type="submit" class="btn btn-primary">Search</button>
</form>
</div>

</body>
</html>