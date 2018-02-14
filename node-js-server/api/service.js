const brain = require('brain');
const request = require('sync-request');

const util = require('./util');


var net = new brain.NeuralNetwork();


module.exports.getData = function(days) {
	var url = 'http://www.nbrb.by/API/ExRates/Rates/Dynamics/145?startDate=' + util.getDStartDate() + '&endDate=' + util.getEndDate();
	var response = request('GET', url);

	var data = JSON.parse(response.getBody('utf-8'));
	var arr = [];

	for (var i = 0; i < data.length; i++) {
		arr.push(data[i].Cur_OfficialRate/10);
	}

	var trainedNN = JSON.parse(util.readNN());
	var outArr = [];

	for (var i = 0; i < days; i++) {
		var output = net.fromJSON(trainedNN).run(arr);
		outArr.push(output[0]*10);
		arr.splice(0, 1);
		arr.push(output[0]);
	}
	
	return outArr;
}
