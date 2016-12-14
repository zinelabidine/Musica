(function () {
  "use strict";

  angular.module('app')
    .controller('ClientCommandeCtrl',
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
        'clientcommandeService',
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService, cartService,checkoutService,profilService,clientcommandeService
        ) {
          var self = this;
          $scope.client = globalService.personalDatas().utilisateurid;
          $scope.resultats = [];       

          loadClientCommande();

          function loadClientCommande() {
            clientcommandeService.getCommande($scope.client).then(function (response) {
                $scope.resultats = response;
                $log.log($scope.resultats);
                $log.log($scope.resultats.length);
            });
          }

          $scope.getCommandeDetails = function(commandeId){
            $location.path("/profil/commande/"+commandeId);
            $log.log("voir detail commande id: "+ commandeId);
          }
         
      }]);
}());