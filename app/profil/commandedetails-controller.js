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
        'pdfService',
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService, cartService,checkoutService,profilService,commandedetailsService,pdfService
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
         
          $scope.openPdf = function() {
              $log.log(
                '[CommandeDetailsCtrl] Open pdf commande '
              );
              checkoutService.getCommande($scope.commandeId).success(function(response) {
                var pdf = pdfService.getCommandeAsPdf(response);
                pdf.open();
                // pdf.getBase64(function(data) {
                //   var facture = {};
                //   facture.commandeid = $scope.commande;
                //   facture.facturebase64 = data;
                //   checkoutService.sendPdf(facture);
                // });
              });

            }
         
      }]);
}());