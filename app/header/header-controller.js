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
        'globalService',
        'registerService',
        '$rootScope',
        function ($scope, $log, $location, headerData, headerService, globalService, registerService, $rootScope) {

          var self = this;

          self.headerData = headerData;
          //$scope.marque = $scope.marque;
          $scope.motcles = "";
          $scope.categories = headerData.categories;
          $scope.cartsize = 0;
          $scope.isConnected = registerService.isConnected();
          
          $scope.searchAction = function () {
            $location.search({});
            $location.path("/recherche").search('motcles', $scope.motcles);
          };

          $scope.cartAction = function () {
            $location.path("/cart");
          };

          setCartSize();

          function setCartSize() {
            $scope.cartsize = 0;
            if (globalService.personalDatas() != null)
            headerService.initCartSize(globalService.personalDatas().utilisateurid)
              .then(function (response) {
                $scope.cartsize = response;
              });
          }

          $rootScope.$on('cartInstrumentChanged', function () {
            console.log("on: cartInstrumentChanged");
            setCartSize();
          });

          $scope.logout = function () {
            registerService.logout();
            $scope.isConnected = registerService.isConnected();
            $location.path('register');
          };

          $scope.profil = function(){
            $location.path("/profil");
          }
          
          $scope.getToRegister = function () {
            $location.path('register');
          };

          $scope.goToCategorie = function (categorieName) {
            $location.search({});
            $location.path("/recherche").search('categorieName', categorieName);
          };

          $rootScope.$on('authentificationEvent', function (event, args) {
            $scope.isConnected = registerService.isConnected();
          });

        }]);

}());
