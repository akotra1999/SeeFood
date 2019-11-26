<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- This page is the first page the user sees -->
<!DOCTYPE html>

<html>
<html lang="en">
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>SeeFood</title>

</head>

<body>

	<div class="container">

		<!--  the jumbotron contains the title, a description, and a button that takes the user to our code -->
		<div class="jumbotron">

			<h1 class="display-4">SeeFood</h1>
			<p class="lead">This webapp helps users choose a restaurant.</p>
			<hr class="my-4">
			<a class="btn btn-primary"
				href="https://github.com/akotra1999/SeeFood" role="button">Take
				a look at our code</a>
		</div>


		<!--  this form is for the user to enter cuisine, price, and distance info -->
		<form method="post">

			<div class="row">

				<div class="col-sm-4">

					<select class="selectpicker" name="cuisine">
						<option selected>Cuisine</option>
						<option value="Japanese">Japanese</option>
						<option value="Chinese">Chinese</option>
						<option value="Indian">Indian</option>
						<option value="Mexican">Mexican</option>
						<option value="American">American</option>
						<option value="Mediterranean">Mediterranean</option>
					</select>

				</div>

				<div class="col-sm-4">

					<select class="selectpicker" name="distance">
						<option selected>Distance</option>
						<option value="1">1 mi</option>
						<option value="5">5 mi</option>
						<option value=10">10 mi</option>
						<option value="20">20 mi</option>
					</select>

				</div>

				<div class="col-sm-4">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>

			</div>

		</form>

	</div>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>

</html>