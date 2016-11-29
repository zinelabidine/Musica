(function () {
  "use strict";

  angular.module('app')
    .controller('RechercheCtrl', ['$scope', '$stateParams','$http', '$log', '$location','ApplicationConfig','rechercheService', function ($scope,$stateParams, $http, $log, $location, ApplicationConfig, rechercheService) {
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
      
    }]);
}());