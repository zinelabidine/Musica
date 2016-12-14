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
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, checkoutService, $rootScope, pdfService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.commande = $stateParams.commande;
            $scope.orderByReverse = false;

            $scope.openPdf = function() {
              $log.log(
                '[DisplayCommandeController] Open pdf commande '
              );
              checkoutService.getCommande($scope.commande).success(function(response) {
                var pdf = pdfService.getCommandeAsPdf(response);
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
