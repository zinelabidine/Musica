/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .controller('DetailsInstCtrl', ['$scope', '$http', '$log', '$location', '$stateParams', 'instrumentService', 'headerService', function ($scope, $http, $log, $location, $stateParams, instrumentService, headerService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;
      $scope.instrumentId = $stateParams.instrumentId;
      $scope.categories = headerService.getCategories();

      instrumentService.getDetailsInstrument($scope.instrumentId).then(function (response) {
        $log.log("Init détails instr");
        $scope.detailsInstrument = response;
      });

      $scope.reloadMainImage = function (newSrc) {
        $scope.mainImageSrc = newSrc;
      }
    }]);
}());