(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl',
        [
          '$scope',
          '$log',
          '$location',
          'headerData',
          'headerService',
          '$rootScope',
          function ($scope, $log, $location, headerData, headerService, $rootScope) {

      var self = this;

      self.headerData = headerData;
      $scope.marque = $scope.marque;
      $scope.categorie = $scope.categorie;
      $scope.motcles = $scope.motcles ;
      $scope.categories = headerData.Categories;

      $scope.cartsize = 0;

      $scope.searchAction = function () {       
        $location.path("/recherche/"+$scope.motcles);
      };

      $scope.cartAction = function () {
        $location.path("/cart");
      };

      setCartSize();

      function setCartSize() {
        headerService.initCartSize(1)
        .then(function (response) {
          $scope.cartsize = response.data;
        }, function (errResponse) {
          $log.log("Error in AJAX call " + errResponse);
        });
      }

      $rootScope.$on('cartInstrumentChanged', function () {
        console.log("on: cartInstrumentChanged");
          setCartSize();
      });

      //headerService.initHeader().then(function (response) {
      //  $log.log("initHeader-service");
      //  $scope.categories = response;
      //});

    }]);

}());
