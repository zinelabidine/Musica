(function () {
  "use strict";

  angular.module('app')
    .controller('AddInstCtrl', ['$scope', '$http', '$log', '$location', 'ApplicationConfig', 'addinstrumentService', function ($scope, $http, $log, $location, ApplicationConfig, addinstrumentService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;
      $scope.marqueInstrument = $scope.marqueInstrument;
      $scope.categorieInstrument = $scope.categorieInstrument;
      $scope.referenceInstrument = $scope.referenceInstrument;
      $scope.quantiteInstrument = $scope.quantiteInstrument;
      $scope.prixInstrument = $scope.prixInstrument;
      $scope.descriptionInstrument = $scope.descriptionInstrument;
      $scope.imageInstrument = $scope.imageInstrument;

      $scope.readFile = function () {            
      $log.log("read file");
      fileReader.readAsDataUrl($scope.file, $scope)
                .then(function(result) {
                      $scope.imageSrc = result;
                  });
      };

      $scope.addInstrument  = function (){
          addinstrumentService.addInstrument($scope.marqueInstrument,$scope.categorieInstrument,$scope.referenceInstrument,
              $scope.quantiteInstrument,$scope.prixInstrument,$scope.descriptionInstrument,$scope.imageInstrument)
          .success(function() {
              $log.log("success");      
          });
      }

    }]);
}());