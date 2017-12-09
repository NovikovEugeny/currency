const fs = require('fs');

module.exports.getYStartDate = function () {
	var date = new Date();
	date.setFullYear(date.getFullYear() - 1);

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	return year + '-' + month + '-' + day;
}

module.exports.getDStartDate = function () {
	var date = new Date();
	date.setDate(date.getDate() - 4);

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	return year + '-' + month + '-' + day;
}

module.exports.getEndDate = function () {
	var date = new Date();

	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	return year + '-' + month + '-' + day;
}

module.exports.writeNN = function (json) {
	fs.writeFile('../data/trained-nn.txt', json, function (err) {
		if (err) throw err;
	});
}

module.exports.readNN = function () {
	return fs.readFileSync('../data/trained-nn.txt', 'utf8');
}
