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
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, cartService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.resultats =[];
            $scope.motcles = $stateParams.motcles;
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
              });
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
                console.log('[cartController] Success instrument update');
              }).error(function(error) {
                console.log('[cartController] Error instrument update. ' + error);
                // TODO display error message to client
              });
            }

            $scope.validateCart = function(panierid) {
              $log.log('[cartController] Validate cart ' + panierid);
              cartService.validateCart($scope.motcles, panierid)
              .success(function() {
                console.log('[cartController] Success cart ' + panierid+ ' validate');
                $location.path("checkout/" + $scope.motcles);
              }).error(function(error) {
                console.error('[cartController] Error cart ' + panierid+ ' validate' + error);
                // TODO display error message to client
              });
            }

            getCart();

            function getCart() {
              cartService.getResultats($scope.motcles).then(function (response) {
                  $scope.resultats = response;
                  $log.log($scope.resultats);
                });
            }

    }]);
}());
