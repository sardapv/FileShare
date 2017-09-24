var express = require("express");
var app = express();
var bodyparser = require("body-parser");

	var posts = [
	"pranav",
	"yash",
	"vinod",
	"archana"
	]

app.use(bodyparser.urlencoded({extended:true}));
app.use(express.static("public"));
app.set("view engine", "ejs");
app.get("/", function(req,res){
	res.render("home");
});

app.get("/fallinlovewith/:thing", function(req,res){
	var thing = req.params.thing;
	res.render("love",{thingVar : thing});
});

app.post("/addfriend", function(req,res){
	var newf = req.body.newfriend;
	posts.push(newf);
	res.redirect("/posts");
});
app.get("/posts",function(req,res){
		res.render("posts", {posts:posts});
});

app.listen(3000,process.env.ip,function(){
	console.log("Server Started");
});
