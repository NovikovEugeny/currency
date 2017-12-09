const brain = require('brain');
const request = require('sync-request');

const util = require('./util');

var net = new brain.NeuralNetwork({
	hiddenLayers: [10,20,30],
	learningRate: 0.8
});


function train() {

	var url = 'http://www.nbrb.by/API/ExRates/Rates/Dynamics/145?startDate=' + util.getYStartDate() + '&endDate=' + util.getEndDate();
	var response = request('GET', url);

	var data = extractRates(response.getBody('utf-8'));

	console.log(data);

	console.log('Идет обучение...');

	net.train(data, {
		errorThresh: 0.00002,
	});

	var json = JSON.stringify(net.toJSON());
	util.writeNN(json);

	console.log('Нейронная сеть обучена!');
}


function extractRates(json) {
	var data = [];
	
	var list = JSON.parse(json);

	for (var i = 0; i < list.length - 5; i++) {
		var obj = {};
		obj.input = [];
		obj.output = [];

		for (var j = i; j < i + 5; j++ ) {
			obj.input.push(list[j].Cur_OfficialRate / 10);
		}

		obj.output.push(list[i + 5].Cur_OfficialRate / 10);
		data.push(obj);
	}
	
	return data;
}

train();