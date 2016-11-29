(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', '$log', '$location', 'headerData', 'headerService', function ($scope, $log, $location, headerData, headerService) {

      var self = this;

      self.headerData = headerData;
      $scope.marque = $scope.marque;
      $scope.categorie = $scope.categorie; 
      $scope.motcles = $scope.motcles ;
      $scope.categories = headerData.Categories;

      $scope.searchAction = function () {
        if ($scope.marque === undefined){
          $scope.marque = "";
        }

        if ($scope.categorie === undefined){
          $scope.categorie = "";
        }

        if ($scope.motcles === undefined){
          $scope.motcles = "";
        }
        
        $location.path("/recherche/" + $scope.marque+"&"+$scope.categorie+"&"+$scope.motcles);

        $scope.marque = "";
        $scope.categorie = "";
        $scope.motcles = "";
      };

      $scope.cartAction = function () {
        $location.path("/cart");
      };

      //headerService.initHeader().then(function (response) {
      //  $log.log("initHeader-service");
      //  $scope.categories = response;
      //});

    }]);

}());