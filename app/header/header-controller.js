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
          $scope.motcles = $scope.motcles;
          $scope.categories = headerData.categories;

          $scope.cartsize = 0;

          $rootScope.currentclientid = 1;

          $scope.searchAction = function () {
            $location.path("/recherche/" + $scope.motcles);
          };

          $scope.cartAction = function () {
            $location.path("/cart");
          };

          setCartSize();

          function setCartSize() {
	           $scope.cartsize = 0;
             headerService.initCartSize($rootScope.currentclientid)
             .then(function (response) {
                $scope.cartsize = response;
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
