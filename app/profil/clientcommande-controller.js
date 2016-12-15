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
          $scope.avoirCommande = false; 

          loadClientCommande();

          function loadClientCommande() {
            clientcommandeService.getCommande($scope.client).then(function (response) {
                $scope.resultats = response;
                if(angular.isEmpty($scope.resultats)){
                      $scope.nombreCommandes = 0;
                  }
                else{
                      $scope.nombreCommandes = $scope.resultats.length
                      $scope.avoirCommande = true;
                  }
                $log.log($scope.resultats);
                $log.log($scope.nombreCommandes);
            });
          }

          $scope.getCommandeDetails = function(commandeId){
            $location.path("/profil/commande/"+commandeId);
            $log.log("voir detail commande id: "+ commandeId);
          }
         
      }]);
}());