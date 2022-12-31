window.onload = () => {
    const calcButton = document.querySelector("#calc-button");
    const fibNumberInput = document.querySelector("#fib-n-input");
    let resultDiv =document.querySelector("#result");

    calcButton.addEventListener("click", () => {
        fetch("/calculate",
            {
                method:'POST',
                headers: {'Content-Type': 'application/json'},
                body: `${fibNumberInput.value}`
            })
            .then( response =>{ 
                return response.text();
            })
            .then(data =>{
                resultDiv.innerHTML = data;
            })
    });
}