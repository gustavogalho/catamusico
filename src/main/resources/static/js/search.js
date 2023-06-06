window.addEventListener('load', function () {
    get_states();

    document.getElementById("state").addEventListener('click', function () {
        console.log(document.getElementById("state").value)
        get_cities();
    });

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

        const option = document.createElement("option");
        option.value = "";
        option.text = "Cidade";
        selectCity.add(option);
        
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
});