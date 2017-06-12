function factorial(num){
	if (num >= 0) {
		var fact = 1;
		for (var i = num; i > 0; i--) {
			fact = fact * i;
		}
		return fact;
	}
	else{
		return "Not defined";
	}
}
function replaceit(line){
	line = line.replace(/-/g, "_");
	return line;
}

var temp = prompt("Enter a Number : ")
alert("Replaced string is " + replaceit(temp))
// alert("Factorial is " + factorial(temp))