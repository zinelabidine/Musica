(function () {
  "use strict";

  angular.module('app')
    .controller('CartCtrl',
      [
        '$scope',
        '$stateParams',
        '$http',
        '$log',
        '$location',
        'ApplicationConfig',
        'cartService',
        '$rootScope',
        'globalService',
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, cartService, $rootScope, globalService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.resultats =[];
            $scope.client = $stateParams.client;
            $scope.orderByReverse = false;

            $scope.deleteInstrumentPanier = function(cartId, instrumentId) {
              $log.log(
                '[cartController] Delete instrument '
                + instrumentId
                + ' from cart '
                + cartId
              );
              cartService.deleteInstrumentPanier(cartId, instrumentId)
              .then(function() {
                getCart();
                $scope.$emit('cartInstrumentChanged');
              });
            }

            $scope.backPaymentInformation = function() {
                $location.path("checkout/paymentinfo");
            }

            $scope.updateCartInstrumentQuantity = function(instrumentId, quantite) {
              $log.log(
                '[cartController] Update instrument '
                + instrumentId
                + ' quantite to '
                + quantite
              );
              cartService.updateCartInstrumentQuantity(instrumentId, quantite)
              .success(function() {
                $log.log('[cartController] Success instrument update');
                getCart();
              }).error(function(error) {
                $log.log('[cartController] Error instrument update. ' + error);
                // TODO display error message to client
              });
            }

            $scope.finaliseCommande = function() {
              $log.log(
                '[cartController] Finalize commande of client ' + globalService.personalDatas().utilisateurid
              );
              cartService.finaliseCommande(globalService.personalDatas().utilisateurid).
              then(function(response) {
                $location.path("checkout/displaycommand/" + response.data);
              });
            }

            $scope.validateCart = function(panierid) {
              $log.log('[cartController] Validate cart ' + panierid);
              cartService.validateCart(globalService.personalDatas().utilisateurid, panierid)
              .success(function() {
                console.log('[cartController] Success cart ' + panierid+ ' validate');
                $location.path("checkout/personalinfo");
              }).error(function(error) {
                console.error('[cartController] Error cart ' + panierid+ ' validate' + error);
                // TODO display error message to client
              });
            }

            getCart();

            function getCart() {
              cartService.getCart(globalService.personalDatas().utilisateurid).then(function (response) {
                  $scope.resultats = response;
                  $log.log($scope.resultats);
                });
            }

    }]);
}());
