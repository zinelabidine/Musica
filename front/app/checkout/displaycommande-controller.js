(function () {
  "use strict";

  angular.module('app')
    .controller('DisplayCommandeCtrl',
      [
        '$scope',
        '$stateParams',
        '$http',
        '$log',
        '$location',
        'ApplicationConfig',
        'checkoutService',
        '$rootScope',
        'pdfService',
        'globalService',
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, checkoutService, $rootScope, pdfService, globalService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.commande = $stateParams.commande;
            $scope.orderByReverse = false;
            $scope.client = globalService.personalDatas().utilisateurid;

            getClient();

            function getClient(){
              checkoutService.getClient($scope.client).then(function(response){                  
                  $scope.clientInfo = response;
                });
            }

            $scope.openPdf = function() {
              $log.log(
                '[DisplayCommandeController] Open pdf commande '
              );
              checkoutService.getCommande($scope.commande).success(function(response) { 
                var pdf = pdfService.getCommandeAsPdf(response,$scope.clientInfo);
                pdf.open();
                pdf.getBase64(function(data) {
                  var facture = {};
                  facture.commandeid = $scope.commande;
                  facture.facturebase64 = data;
                  checkoutService.sendPdf(facture);
                });
              });

            }

    }]);
}());
