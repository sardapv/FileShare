var express = require("express");
var app = express();

//single route for get request on root dir
app.get("/",function(req,res){
	res.send("Hi there!");
});
app.get("/speak/:animal",function(req,res){
	var animal = req.params.animal;
	if(animal === "pig")
		res.send("The "+animal+"says Oink");
	else if(animal === "cow")
		res.send("The "+animal+"says Moo");
	else if(animal === "dog")
		res.send("The "+animal+"says woof woof");
});
app.get("/repeat/:say/:num",function(req,res){
	var saying  = req.params.say;
	var num = Number(req.params.num);
	var result = saying;
	for(var i=0; i < num ; i++)
		result  = result +" "+saying;
	res.send(result);
});
app.get("*",function(req,res){
	res.send("Sorry no page found! What are you doing with your life");
});
app.listen(3000, process.env.IP,function(){
	console.log("Server Started !!");
});
