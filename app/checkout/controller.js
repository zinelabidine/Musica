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
        '$rootScope',
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, checkoutService, $rootScope
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.client = $rootScope.currentclientid;
            $scope.orderByReverse = false;

            $scope.updateClientPersonalInformation = function() {
              $log.log(
                '[CheckoutController] update personal client information'
              );
              $log.log($scope.resultat_client);
              $scope.resultat_client.utilisateurid = $scope.client;
              checkoutService.updateClientPersonalInformation($scope.resultat_client)
              .success(function() {
                $location.path("checkout/paymentinfo");
              });
              // TODD go to payment form
            }

            $scope.backPersonalInformation = function() {
              $location.path("checkout/personalinfo");
            }

            $scope.updateClientPaymentInformation = function() {
              $log.log(
                '[CheckoutController] update payment client information'
              );
              $log.log($scope.resultat_client);
              $scope.resultat_client.utilisateurid = $scope.client;
              checkoutService.updateClientPaymentInformation($scope.resultat_client)
              .success(function() {
                $log.log(
                  '[CheckoutController] Success update payment client information'
                );
                $location.path("checkout/orderreview");
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
