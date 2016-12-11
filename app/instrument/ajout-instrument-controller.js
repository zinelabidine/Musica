(function () {
  "use strict";

  angular.module('app')
    .controller('AddInstCtrl', ['$scope', '$http', '$log', '$location','catmarData', 'ApplicationConfig', 'addinstrumentService', function ($scope, $http, $log, $location,catmarData, ApplicationConfig, addinstrumentService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;
      self.catmarData = catmarData;
      //$scope.marqueInstrument = $scope.marqueInstrument;
      //$scope.categorieInstrument = $scope.categorieInstrument;
      //$scope.referenceInstrument = $scope.referenceInstrument;
      //$scope.quantiteInstrument = $scope.quantiteInstrument;
      //$scope.prixInstrument = $scope.prixInstrument;
      //$scope.descriptionInstrument = $scope.descriptionInstrument;
      $scope.imageInstrument = "";

      $scope.categories = catmarData.categories;
      $scope.marques = catmarData.marques;

      var marque;
      var categorie;

      $scope.addImage = function(){
        $scope.imageInstrument = $scope.dummy.images[0];
      };

      $scope.selectMarque = function(marque) {
        $log.log( $scope.marqueSelected );
      };

      $scope.addInstrument  = function (){
        if($scope.marqueSelected !== null){
          marque = $scope.marqueSelected.libelle;
        }  
        else{
          marque = $scope.autreMarque;
        }
        $log.log( marque );    

        if($scope.categorieSelected !== null){
          categorie = $scope.categorieSelected.libelle;
        }  
        else{
          categorie = $scope.autreCategorie;
        }
        $log.log( categorie );   

        addinstrumentService.addInstrument(marque,categorie,$scope.referenceInstrument,
            $scope.quantiteInstrument,$scope.prixInstrument,$scope.descriptionInstrument,$scope.imageInstrument)
        .success(function() {
            $scope.alert ="Added a instrument with success !!!";
            $log.log("success");      
        });
      };

    }]);
}());