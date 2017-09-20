var app = angular.module('wineSearcher', []);
var variables;

$('#main-body').fadeIn(1000);

app.controller("WineController", function ($scope, $http) {
	
	$scope._getById = function() {
		_getById();
	}
	
	$scope._getByAmount = function() {
		_getByAmount();
	}
	
	$scope._getByPage = function() {
		_getByPage();
	}
	
	// Get JSON object by Id
	function _getById() {
		var id = $scope.searchId;
		
		if (id < 0) {
			$scope.error_message = "Invalid input";
			toggleAlert();
		} else {
			// Get one property
			$http({
				method: 'GET',
				url: 'wine/find/' + id
			}).then(function success(response) {
				$scope.obj_return_small = response.data;
			}, function error(response) {
				$scope.obj_return_small = "";
				$scope.error_message = response.statusText;
				toggleAlert();
			});
			
			// Get five property
			$http({
				method: 'GET',
				url: 'wine/find/' + id + '/bonus'
			}).then(function success(response) {
				$scope.obj_return_large = response.data;
			}, function error(response) {
				$scope.obj_return_large = "";
				$scope.error_message = response.statusText;
				toggleAlert();
			});
		}
	}
	
	// Get JSON array for first N JSON object
	function _getByAmount() {
		var amount = $scope.searchAmount;
		if (amount < 0) {
			$scope.error_message = "Invalid input";
			toggleAlert();
		} else {
			$http({
				method: 'GET',
				url: 'wine/first/' + amount
			}).then(function success(response) {
				$scope.firstNData = response.data;
			}, function error(response) {
				$scope.firstNData = [];
				$scope.error_message = response.statusText;
				toggleAlert();
			});
		}
	}
	
	// Get JSON object for page N
	function _getByPage() {
		var page = $scope.searchPage;
		if (page < 0) {
			$scope.error_message = "Invalid input";
			toggleAlert();
		} else {
			$http({
				method: 'GET',
				url: 'wine/page/' + page
			}).then(function success(response) {
				$scope.pageData = response.data;
			}, function error(response) {
				$scope.pageData = [];
				$scope.error_message = response.statusText;
				toggleAlert();
			});
		}
	}
});

// Toggle alert
function toggleAlert() {
	$('#div-error').fadeIn();
	setTimeout(function() {
		$('#div-error').fadeOut();
	}, 5000);
}