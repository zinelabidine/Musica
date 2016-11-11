(function () {
  "use strict";

  angular.module('app')
    .controller('HomeCtrl', ['$scope', '$http', '$location', 'homeService', function ($scope, $http, $location, homeService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      var instrumentDestination = "instrument";
      $scope.meilleurVente = [];
      homeService.getMeilleurVente().then(function (response) {
        $scope.meilleurVente = response;
      });
      $scope.promotions = homeService.getPromotions();
      $scope.bestSales = homeService.getBestSales();

      $scope.goToInstrument = function (id) {
        console.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };

    }]);
}());