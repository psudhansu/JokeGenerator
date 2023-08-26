// JavaScript code to get the value
// Select the input element by its id
let nameInput = document.getElementById("name-input");
let output = document.getElementById("joke-result");
// Get the value of the input element
let nameValue = nameInput.value;
// Do something with the value
console.log(nameValue); // John

// Select the button element by its id
let jokeBtn = document.getElementById("joke-btn");
// Add a click event listener to the button
jokeBtn.addEventListener("click", function() {
  // Do something when the button is clicked
  // Fetch data from a URL
fetch(`http://localhost:8080/bot/chat?prompt=${nameValue}`)
// Convert the response to JSON
.then(response => response.json())
// Do something with the data
.then(data => output.innerText=data)
// Handle any errors
.catch(error => console.error(error));
  console.log("Button clicked!");
});
