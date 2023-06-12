window.addEventListener('load', function () {
	get_states();
	document.getElementById("state").addEventListener('click', function () {
		get_cities();
	});

	if (!stateIsSelected()) {
		select_state();
	}
	if (!cityIsSelected()) {
		console.log('!cityIsSelected')
		select_city();
	}


	function select_state() {
		const selectState = document.getElementById("state");
		Array.prototype.forEach.call(selectState.options, function (option) {
			if (selectState.getAttribute("value") != null) {
				if (option.value.split("-")[1] == selectState.getAttribute("value")) {
					option.selected = true;
				}
			}
		});
		selectState.click();
	}

	function select_city() {
		const selectCity = document.getElementById("city");
		const option = document.createElement("option");
		option.value = selectCity.getAttribute("value");
		option.text = selectCity.getAttribute("value");
		selectCity.add(option);
	}

	function stateIsSelected() {
		const selectState = document.getElementById("state");
		Array.prototype.forEach.call(selectState.options, function (option) {
			if (option.value != "" && option.selected) {
				return true;
			}
		});
		return false;
	}

	function cityIsSelected() {
		const selectCity = document.getElementById("city");
		Array.prototype.forEach.call(selectCity.options, function (option) {
			console.log(option.value != "" && option.selected);
			if (option.value != "" && option.selected) {
				return true;
			}
		});
		return false;
	}

	function sleep(ms) {
		return new Promise(resolve => setTimeout(resolve, ms));
	}

function get_states() {
	console.log('get_states')
	const selectState = document.getElementById("state");
	fetch("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome")
		.then(response => response.json())
		.then(states => {
			states.forEach(state => {
				const option = document.createElement("option");
				option.value = state.id + "-" + state.nome;
				option.text = state.nome;
				selectState.add(option);
				if (state.nome == selectState.getAttribute("value")) {
					option.selected = true;
				}
			});
		})
		.catch(error => console.log(error));
}
});

window.addEventListener('loadeddata', function () {
	if (!stateIsSelected()) {
		select_state();
	}
	if (!cityIsSelected()) {
		console.log('!cityIsSelected')
		select_city();
	}


	function select_state() {
		const selectState = document.getElementById("state");
		Array.prototype.forEach.call(selectState.options, function (option) {
			if (selectState.getAttribute("value") != null) {
				if (option.value.split("-")[1] == selectState.getAttribute("value")) {
					option.selected = true;
				}
			}
		});
		selectState.click();
	}

	function select_city() {
		const selectCity = document.getElementById("city");
		const option = document.createElement("option");
		option.value = selectCity.getAttribute("value");
		option.text = selectCity.getAttribute("value");
		selectCity.add(option);
	}

	function stateIsSelected() {
		const selectState = document.getElementById("state");
		Array.prototype.forEach.call(selectState.options, function (option) {
			if (option.value != "" && option.selected) {
				return true;
			}
		});
		return false;
	}

	function cityIsSelected() {
		const selectCity = document.getElementById("city");
		Array.prototype.forEach.call(selectCity.options, function (option) {
			console.log(option.value != "" && option.selected);
			if (option.value != "" && option.selected) {
				return true;
			}
		});
		return false;
	}

	function sleep(ms) {
		return new Promise(resolve => setTimeout(resolve, ms));
	}
});


function get_cities() {
	const selectCity = document.getElementById("city");
	while (selectCity.options.length > 0) {
		selectCity.options[0].remove();
	}
	var stateId = document.getElementById("state").value.split("-")[0];
	console.log(stateId)
	fetch("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + stateId + "/municipios?orderBy=nome")
		.then(response => response.json())
		.then(cities => {
			cities.forEach(city => {
				const option = document.createElement("option");
				option.value = city.nome;
				option.text = city.nome;
				selectCity.add(option);
				if (city.nome == selectCity.getAttribute("value")) {
					option.selected = true;
				}
			});
		})
		.catch(error => console.log(error));
}