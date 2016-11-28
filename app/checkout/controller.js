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

            $scope.resultat_client =[];
            $scope.resultat_client =[];
            $scope.client = $stateParams.client;
            $scope.orderByReverse = false;

            $scope.updateClientPersonalInformation = function() {
              $log.log(
                '[CheckoutController] update personal client information'
              );
              $log.log($scope.resultat_client);
              $scope.resultat_client.clientid = $scope.client;
              checkoutService.updateClientPersonalInformation($scope.resultat_client)
              .success(function() {
                $location.path("checkout/paymentinfo/" + $scope.client);
              });
              // TODD go to payment form
            }

            $scope.backPersonalInformation = function() {
              $location.path("checkout/personalinfo/" + $scope.client);
            }

            $scope.updateClientPaymentInformation = function() {
              $log.log(
                '[CheckoutController] update payment client information'
              );
              $log.log($scope.resultat_client);
              $scope.resultat_client.clientid = $scope.client;
              checkoutService.updateClientPaymentInformation($scope.resultat_client)
              .success(function() {
                $log.log(
                  '[CheckoutController] Success update payment client information'
                );
                $location.path("checkout/orderreview/" + $scope.client);
              });
              // TODD go to payment form
            }

            loadClient();

            function loadClient() {
              checkoutService.getClient($scope.client).then(function (response) {
                  $scope.resultat_client = response;
                  $log.log($scope.resultat_client);
                });
            }
    }]);
}());
