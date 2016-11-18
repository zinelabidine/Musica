(function () {
  "use strict";

  angular.module('app')
    .controller('RechercheCtrl', ['$scope', '$stateParams','$http', '$log', '$location','ApplicationConfig','rechercheService', function ($scope,$stateParams, $http, $log, $location, ApplicationConfig, rechercheService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      $scope.resultats =[];
      $scope.resultats =[];
      $scope.motcles = $stateParams.motcles;
      $scope.options = ["Nom", "Prix"];
      $scope.orderByPredicate = "reference";
      $scope.orderByReverse = false;

      search();

      function search() {
        $log.log("key:" + $scope.motcles); 
        rechercheService.getResultats($scope.motcles).then(function (response) {          
            $scope.resultats = response;
            $log.log($scope.resultats); 
          });
      }

      $scope.filtre = function() {
        if ($scope.selection =="Nom" ){
          $scope.orderByReverse = !$scope.orderByReverse;
          $scope.orderByPredicate = 'reference';
        }
        else if ($scope.selection =="Prix"){
          $scope.orderByReverse = !$scope.orderByReverse;
          $scope.orderByPredicate = 'prix';
        }
      }

      $scope.goToInstrument = function (id) {
        $log.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };
      
    }]);
}());