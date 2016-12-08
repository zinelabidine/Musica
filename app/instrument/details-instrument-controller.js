/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .controller('DetailsInstCtrl',
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
        function (
          $scope, $http, $log, $location, $stateParams, instrumentService, headerService, $rootScope, globalService
        ) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;
      // TODO selecte current user autenticated
      $scope.utilisateurid = globalService.personalDatas().utilisateurid;
      $scope.instrumentId = $stateParams.instrumentId;
      // TODO Add window to select quantite to add
      $scope.quantite = 1;
      $scope.categories = headerService.getCategories();

      instrumentService.getDetailsInstrument($scope.instrumentId).then(function (response) {
        $log.log("Init détails instr");
        $scope.detailsInstrument = response;
      });

      $scope.reloadMainImage = function (newSrc) {
        $scope.mainImageSrc = newSrc;
      }

      $scope.addInstrumentToCart = function() {
        $log.log("[DetailsInstCtrl] Add instrument to cart");
        instrumentService.addInstrumentToCart(
          $scope.utilisateurid,
          $scope.instrumentId,
          $scope.quantite
        )
        .success(function() {
          $log.log("[DetailsInstCtrl] Add instrument to cart end successfully");
          $rootScope.$broadcast('cartInstrumentChanged');
          // TODO notify client instrument add successfully
        });
      }
    }]);
}());