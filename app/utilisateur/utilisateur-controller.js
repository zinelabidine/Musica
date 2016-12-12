(function () {
  "use strict";

  angular.module('app')
    .controller('ProfilCtrl',
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
        'checkoutService',
        'profilService',
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService, cartService,checkoutService,profilService
        ) {
      // The content of the controller.
      // Instead of using this use the variable self.
          var self = this;
          $scope.client = globalService.personalDatas().utilisateurid;
          
          loadClient();

          function loadClient() {
            checkoutService.getClient($scope.client).then(function (response) {
                $scope.resultat_client = response;
                $log.log("info:" + $scope.resultat_client);
              });
          }

          $scope.updateClientPersonalInformation = function() {
              $log.log(
                '[ProfilContrller] update personal client information'
              );
              $log.log($scope.resultat_client);
              $scope.resultat_client.utilisateurid = $scope.client;
              checkoutService.updateClientPersonalInformation($scope.resultat_client)
              .success(function() {
                $scope.alert ="Update client personal information avec success !!!"   
              });
            }
      }]);
}());