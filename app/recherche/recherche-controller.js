(function () {
  "use strict";

  angular.module('app')
  .filter('startFrom', function() {
      return function(input, start) {
          console.log('> startForm: ' + input);
          start = +start; //parse to int
          return input.slice(start);
      }
  })
    .controller('RechercheCtrl',
      [
          '$scope',
          '$stateParams',
          '$http',
          '$log',
          '$location',
          'ApplicationConfig',
          'rechercheService',
          'globalService',
          '$rootScope',
          'cartService',
          function (
              $scope,
              $stateParams,
              $http,
              $log,
              $location,
              ApplicationConfig,
              rechercheService,
              globalService,
              $rootScope,
              cartService
            ) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      $scope.resultats =[];

      // $scope.marque = $stateParams.marque;
      // $scope.categorie = $stateParams.categorie; 
      $scope.motcles = $stateParams.motcles;

      $scope.marque = $scope.marque;
      $scope.categorie = $scope.categorie;  
      $scope.ref = $scope.ref;

      $scope.options = ["Nom", "Prix croissant", "Prix décroissant" , "Marque"];
      $scope.orderByPredicate = "reference";
      $scope.orderByReverse = true;

      $scope.currentPage = 0;
      $scope.pageSize = 8;
      $scope.numberOfPages=function(){
          return Math.ceil($scope.resultats.length/$scope.pageSize);
      }



      search();

      function search() {
        // if (($scope.marque !== "")||($scope.categorie !== "")){
        //   $log.log("Search avance");
        //   rechercheService.searchAvance($scope.marque,$scope.categorie,$scope.motcles).then(function (response) {          
        //     $scope.resultats = response;
        //     $log.log($scope.resultats); 
        //   });
        // }
        // else{
          rechercheService.searchWithKey($scope.motcles).then(function (response) { 
            $log.log("Search with key:" +$scope.motcles);          
            $scope.resultats = response;
            $log.log($scope.resultats); 
          });
        // }
      }

      $scope.searchAvance  = function (){
        if ($scope.marque === undefined){
          $scope.marque = "";
        }

        if ($scope.categorie === undefined){
          $scope.categorie = "";
        }

        if ($scope.ref === undefined){
          $scope.ref = "";
        }
        
        if (($scope.marque !== "")||($scope.categorie !== "")){
          $log.log("Search avance");
          rechercheService.searchAvance($scope.marque,$scope.categorie,$scope.ref).then(function (response) {          
            $scope.resultats = response;
            $log.log($scope.resultats); 
          });
        }
        else{
          rechercheService.searchWithKey($scope.ref).then(function (response) { 
            $log.log("Search with key:" +$scope.ref);          
            $scope.resultats = response;
            $log.log($scope.resultats); 
          });
        }
        $scope.marque = "";
        $scope.categorie = "";
        $scope.ref = "";
      }

      $scope.filtre = function() {
        if ($scope.selection =="Nom" ){
          $scope.orderByPredicate = '-reference';
        }
        else if ($scope.selection =="Prix croissant"){
          $scope.orderByReverse = !$scope.orderByReverse;
          $scope.orderByPredicate = 'prix';
        }
        else if ($scope.selection =="Prix décroissant"){
          $scope.orderByReverse = !$scope.orderByReverse;
          $scope.orderByPredicate = 'prix';
        }
        else if ($scope.selection =="Marque"){
          $scope.orderByPredicate = 'marque';
        }
      }

      $scope.goToInstrument = function (id) {
        $log.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };

      $scope.addInstrumentToCart = function(instrumentid) {
        $log.log("[rechercheInstCtrl] Add instrument "+instrumentid+" to cart");
        cartService.addInstrumentToCart(
          $scope.utilisateurid,
          instrumentid,
          1
        )
        .success(function() {
          $log.log("[rechercheInstCtrl] Add instrument to cart end successfully");
          $rootScope.$broadcast('cartInstrumentChanged');
          // TODO notify client instrument add successfully
        });
      }

    }]);
}());