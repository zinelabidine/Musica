(function () {
  "use strict";

  angular.module('app')
    .filter('startFrom', function () {
      return function (input, start) {
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
        function ($scope,
                  $stateParams,
                  $http,
                  $log,
                  $location,
                  ApplicationConfig,
                  rechercheService,
                  globalService,
                  $rootScope,
                  cartService,
                  headerService) {
          // The content of the controller.
          // Instead of using this use the variable self.
          var self = this;

          $scope.resultats = [];
          $scope.storedCategories = headerService.getCategories();
          $scope.categorieName = $stateParams.categorieName;
          $scope.marque = $stateParams.marque;
          $scope.ref = $stateParams.motcles;
          $scope.order = $stateParams.order;

          $scope.orderOptions = [
            {propName : "Nom", orderByPredicate: "reference", orderByReverse:false},
            {propName : "Prix croissant", orderByPredicate: "prix", orderByReverse:false},
            {propName : "Prix décroissant", orderByPredicate: "prix", orderByReverse:true},
            {propName : "Marque", orderByPredicate: "marque", orderByReverse:false}
          ];

          $scope.utilisateurid = globalService.personalDatas().utilisateurid;

          $scope.currentPage = 0;
          $scope.pageSize = 8;
          $scope.numberOfPages = function () {
            return Math.ceil($scope.resultats.length / $scope.pageSize);
          };

          // Recherche avec mot clé.
          function searchWithKey (keyword) {
            rechercheService.searchWithKey(keyword).then(function (response) {
              $scope.resultats = response;
            });
          }
          
          $scope.searchAvance = function () {
            if (!angular.isUndefinedOrNull($scope.marque) && $scope.marque !== "") {
              $location.search("marque", $scope.marque);
            } else {
              $location.search("marque", null);
              $scope.marque = "";
            }
            if (!angular.isUndefinedOrNull($scope.categorie) && $scope.categorie !== "") {
              $location.search("categorieName", $scope.categorie);
            } else {
              $location.search("categorieName", null);
              $scope.categorie = "";
            }

            if (!angular.isUndefinedOrNull($scope.ref) && $scope.ref !== "") {
              $location.search("motcles", $scope.ref);
            } else {
              $location.search("motcles", null);
              $scope.ref = "";
            }

            searchAvance();
          };

          function validateAllSearchParams() {
            if (angular.isUndefinedOrNull($scope.marque)) {
              $scope.marque = "";
            }

            if (angular.isUndefinedOrNull($scope.categorie)) {
              $scope.categorie = "";
            }

            if (angular.isUndefinedOrNull($scope.ref)) {
              $scope.ref = "";
            }
          }

          function searchAvance() {
            if (($scope.marque !== "") || ($scope.categorie !== "")) {
              rechercheService.searchAvance($scope.marque, $scope.categorie, $scope.ref)
                .then(function (response) {
                  $scope.resultats = response;
                });
            } else {
              searchWithKey($scope.ref);
            }
          }

          $scope.addInstrumentToCart = function (instrumentid) {
            $log.log("[rechercheInstCtrl] Add instrument " + instrumentid + " to cart");
            cartService.addInstrumentToCart(
              $scope.utilisateurid,
              instrumentid,
              1
              )
              .success(function () {
                $log.log("[rechercheInstCtrl] Add instrument to cart end successfully");
                $scope.$emit('cartInstrumentChanged');
                // TODO notify client instrument add successfully
              });
          };

          $scope.openInstrumentDetails = function (instrumentid) {
            $location.path("instrument/" + instrumentid);
          };

          if (!angular.isUndefinedOrNull($scope.categorieName) && $scope.categorieName !== "") {
            if(!angular.isEmpty($scope.storedCategories)) {
              for (var i = 0; i < $scope.storedCategories.length; i++) {
                if ($scope.storedCategories[i].hasOwnProperty('libelle') && $scope.categorieName.toLowerCase() === $scope.storedCategories[i].libelle.toLowerCase()) {
                  $scope.categorie = $scope.storedCategories[i].libelle;
                  break;
                }
              }
            }
            validateAllSearchParams();
            searchAvance();
          } else if (!angular.isUndefinedOrNull($scope.marque) && $scope.marque !== ""){
            validateAllSearchParams();
            searchAvance();
          } else {
            validateAllSearchParams();
            searchWithKey($scope.ref);
          }
        }]);
}());