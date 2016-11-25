(function () {
  "use strict";

  angular.module('app')
    .controller('CheckoutCtrl',
      [
        '$scope',
        '$stateParams',
        '$http',
        '$log',
        '$location',
        'ApplicationConfig',
        'checkoutService',
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, checkoutService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.resultats =[];
            $scope.client = $stateParams.client;
            $scope.resultats.clientid = $scope.client;
            $scope.orderByReverse = false;

            $scope.updateClientPersonalInformation = function() {
              $log.log(
                '[CheckoutController] update client information'
              );
              $log.log($scope.resultats);
              checkoutService.updateClientPersonalInformation($scope.resultats);
            }
    }]);
}());
