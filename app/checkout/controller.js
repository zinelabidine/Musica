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
            $scope.orderByReverse = false;

            $scope.updateClientPersonalInformation = function() {
              $log.log(
                '[CheckoutController] update client information'
              );
              $log.log($scope.resultats);
              $scope.resultats.clientid = $scope.client;
              checkoutService.updateClientPersonalInformation($scope.resultats);
              // TODD go to payment form
            }

            loadClient();

            function loadClient() {
              checkoutService.getClient($scope.client).then(function (response) {
                  $scope.resultats = response;
                  $log.log($scope.resultats);
                });
            }
    }]);
}());
