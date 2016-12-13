/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .controller('DialogCtrl',
      [
        '$scope',
        '$http',
        '$log',
        '$location',
        '$stateParams',
        'instrumentService',
        'headerService',
        '$rootScope',
        'globalService',
        'cartService',
	'ngDialog',
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService, cartService, ngDialog
        ) {
      
	$scope.goConnectionPage = function() {
		console.log('[DialogCtrl] goConnectionPage');
		$location.path('register');	
		ngDialog.close($scope.opendialog);
	}

    }]);
}());
