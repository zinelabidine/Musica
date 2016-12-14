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
          $log.log('[DialogCtrl] goConnectionPage');
          var switch_url = $location.path();
          $log.log(switch_url);
          $location.path('register').search({"switch_url": switch_url});
          ngDialog.close($scope.opendialog);
        }

    }]);
}());
