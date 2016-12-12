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
          'headerService',
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
              cartService,
              headerService
            ) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      $scope.resultats =[];
      $scope.storedCategories = headerService.getCategories();
      $scope.motcles = $stateParams.motcles;
      $scope.categorieName = $stateParams.categorieName;

      $scope.options = ["Nom", "Prix croissant", "Prix décroissant" , "Marque"];
      $scope.orderByPredicate = "reference";
      $scope.orderByReverse = true;

      $scope.utilisateurid = globalService.personalDatas().utilisateurid;

      $scope.currentPage = 0;
      $scope.pageSize = 8;
      $scope.numberOfPages=function(){
          return Math.ceil($scope.resultats.length/$scope.pageSize);
      };

      if(angular.isUndefinedOrNull($scope.motcles)){
        $scope.motcles = "";
      }

      function search() {
        rechercheService.searchWithKey($scope.motcles).then(function (response) {
          $log.log("Search with key:" +$scope.motcles);
          $scope.resultats = response;
          $log.log($scope.resultats);
        });
      }

      $scope.searchAvance = function (){
        if (angular.isUndefinedOrNull($scope.marque)){
          $scope.marque = "";
        }

        if (angular.isUndefinedOrNull($scope.categorie)){
          $scope.categorie = "";
        }

        if (angular.isUndefinedOrNull($scope.ref)){
          $scope.ref = "";
        }
        
        if (($scope.marque !== "")||($scope.categorie !== "")){
          $log.log("Search avance");
          rechercheService.searchAvance($scope.marque,$scope.categorie,$scope.ref)
          .then(function (response) {
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
        // $scope.marque = "";
        // $scope.categorie = "";
        // $scope.ref = "";
      };

      $scope.filtre = function() {
        if ($scope.selection =="Nom" ){
          $scope.orderByPredicate = '-reference';
        }
        else if ($scope.selection =="Prix croissant"){
          $scope.orderByReverse = false;
          $scope.orderByPredicate = 'prix';
        }
        else if ($scope.selection =="Prix décroissant"){
          $scope.orderByReverse = true;
          $scope.orderByPredicate = 'prix';
        }
        else if ($scope.selection =="Marque"){
          $scope.orderByPredicate = 'marque';
        }
      };

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
      };

      $scope.openInstrumentDetails = function(instrumentid) {
        $location.path("instrument/"+instrumentid);
      };

      if(angular.isUndefinedOrNull($scope.categorieName)){
        $scope.categorie = "";
        search();
      } else {
        $log.info($scope.storedCategories);
        angular.forEach($scope.storedCategories, function(value, key) {
          if (value.hasOwnProperty('libelle') && $scope.categorieName.toLowerCase() === value.libelle.toLowerCase()) {
            $scope.categorie = value.libelle;
          }
        });
        $scope.searchAvance();
      }

      //$scope.initDefaultCategorie = function (categorieName) {
      //  if($scope.categorie === "") {
      //    return false;
      //  } else if($scope.categorie.toLowerCase() === categorieName.toLowerCase()){
      //    return true;
      //  }
      //  return false;
      //};

    }]);
}());