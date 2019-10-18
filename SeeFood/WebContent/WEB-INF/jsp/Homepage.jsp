<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<html lang="en">
<head>

<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta http-equiv="x-ua-compatible" content="ie=edge">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
	integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
	crossorigin="anonymous">

<title>SeeFood</title>

</head>

<body>

	<div class="container">

		<div class="row">
			<!--  the jumbotron contains the title, a description, and a button that takes the user to my code -->
			<div class="jumbotron">
				<h1 class="display-4">SeeFood</h1>
				<p class="lead">This webapp helps users choose a restaurant.</p>
				<hr class="my-4">
				<a class="btn btn-primary"
					href="https://github.com/akotra1999/SeeFood" role="button">Take
					a look at our code</a>
			</div>
		</div>

		<!--  this form is for the user to enter cuisine and price info -->
		<form method="post">
			<div class="row">

				<div class="col-sm-4">



					<div class="input-group">

						<select class="custom-select" id="cuisine">
							<option selected>Cuisine</option>
							<option value="Japanese">Japanese</option>
							<option value="Chinese">Chinese</option>
							<option value="Indian">Indian</option>
							<option value="Mexican">Mexican</option>
							<option value="American">American</option>
							<option value="Mediterranean">Mediterranean</option>
						</select>
					</div>
				</div>

				<div class="col-sm-4">


					<div class="input-group">
						
						<select class="custom-select" id="price">
							<option selected>Price</option>
							<option value="$">$</option>
							<option value="$$">$$</option>
							<option value="$$$">$$$</option>
							<option value="$$$$">$$$$</option>
						</select>
					</div>
				</div>

				<div class="col-sm-4">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</form>

	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
		
	</script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"
		integrity="sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7"
		crossorigin="anonymous"></script>

</body>

</html>