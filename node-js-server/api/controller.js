var express = require('express'),
  app = express(),
  port = 3000;

app.listen(port);

console.log('server started on: ' + port);

var service = require('./service');

app.get('/get-day', function(req, res) {
	var json = service.getData(1);
	res.send(json);
});

app.get('/get-week', function(req, res) {
	var json = service.getData(7);
	res.send(json);
});

app.get('/get-month', function(req, res) {
	var json = service.getData(30);
	res.send(json);
});
