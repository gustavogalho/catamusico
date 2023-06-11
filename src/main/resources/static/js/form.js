window.addEventListener('load', function() {
	get_states();
	show_hide();
	var radios = document.querySelectorAll('input[name="profileType"]');
	radios = [].slice.call(radios);

	radios.forEach(function(each) {
		each.addEventListener('change', function() {
			show_hide();
		});
	});
	document.getElementById("state").addEventListener('click', function() {
		console.log(document.getElementById("state").value)
		get_cities();
	});
	get_cities();

	function show_hide() {
		var profile = document.querySelector('input[name=profileType]:checked').value

		if (profile == 'band') {
			$("#instrument-div").hide();
			$("#experienceLevel-div").hide();
		} else {
			$("#instrument-div").show();
			$("#experienceLevel-div").show();;
		}
	};

	function get_states() {
		const selectState = document.getElementById("state");

		fetch("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome")
			.then(response => response.json())
			.then(states => {
				states.forEach(state => {
					const option = document.createElement("option");
					option.value = state.id + "-" + state.nome;
					option.text = state.nome;
					selectState.add(option);
				});
			})
			.catch(error => console.log(error));
	}

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
				console.log(cities)
				cities.forEach(city => {
					const option = document.createElement("option");
					option.value = city.nome;
					option.text = city.nome;
					selectCity.add(option);
				});
			})
			.catch(error => console.log(error));
	}

	document.getElementById("form-login").addEventListener('submit', function(event) {
		var mediaElement = document.getElementById("media");
		if (mediaElement.files.length === 0) {
			event.preventDefault();
			errorAlert("Insira pelo menos um arquivo de foto na sua mídia.");
		}
		
		var profile = document.querySelector('input[name=profileType]:checked').value;
		var instrumentEmpty = document.getElementById("instrument").value == "";
		var experienceEmpty = document.getElementById("experienceLevel").value == "";

		if (profile != 'band' && (instrumentEmpty || experienceEmpty)){
			event.preventDefault();
			errorAlert("Instrumento e nível de experiência são obrigatórios!");
		}
	});

	function errorAlert(message) {
		swal({
			title: "Erro!",
			text: message,
			type: "error",
			showCancelButton: false,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Ok",
			allowOutsideClick: false
		});
	}

});