/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .controller('DetailsInstCtrl', ['$scope', '$http', '$location', 'instrumentService', function ($scope, $http, $location, instrumentService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      $scope.detailsInstrument = instrumentService.getDetailsInstrument();
      $scope.reloadMainImage = function (newSrc) {
        $scope.mainImageSrc = newSrc;
      }
    }]);
}());