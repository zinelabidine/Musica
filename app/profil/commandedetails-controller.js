(function () {
  "use strict";

  angular.module('app')
    .controller('CommandeDetailsCtrl',
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
        'commandedetailsService',
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService, cartService,checkoutService,profilService,commandedetailsService
        ) {
          var self = this;
          $scope.client = globalService.personalDatas().utilisateurid;
          $scope.commandeId = $stateParams.commandeId;
          $scope.resultats = [];       

          loadCommandeDetails();

          function loadCommandeDetails() {
            commandedetailsService.getCommandeDetails($scope.commandeId).then(function (response) {
                $scope.resultats = response;
                $log.log($scope.resultats);
            });
          }

          $scope.retourClientCommandes = function(){
              $location.path("/profil/commande");
          }
         
         
      }]);
}());