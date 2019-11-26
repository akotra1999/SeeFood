<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- This page is for displaying a restaurant to the user -->

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

<style>
.card {
        margin: 10% auto; /* Added */
        float: none; /* Added */
}
</style>

<title>SeeFood</title>

</head>

<body>

	<div class="container">

		<!-- the card contains info regarding the chosen restaurant -->
		<div class="card" style="width: 30rem;">

			<img class="card-img-top" src="https://d.zmtcdn.com/data/pictures/chains/8/16774318/a54deb9e4dbb79dd7c8091b30c642077_featured_v2.png"
				alt="Photo of restaurant">

			<div class="card-body">
				<h5 class="card-title">${name}</h5>
				<p class="card-text">${cuisine}</p>
			</div>

			<ul class="list-group list-group-flush">
				<li class="list-group-item">${address}</li>
				<li class="list-group-item">Phone Number</li>
				<li class="list-group-item">Average rating</li>
			</ul>

			<div class="card-body">
				<a href="#" class="card-link">Restaurant's website</a> <a href="javascript:window.location.reload(true)"
					class="card-link">Pick another restaurant</a>
			</div>

		</div>

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