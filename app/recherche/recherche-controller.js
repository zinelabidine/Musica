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
          $scope.motcles = $stateParams.motcles;
          $scope.categorieName = $stateParams.categorieName;
          $scope.marqueName = $stateParams.marqueName;
          $scope.orderByName = $stateParams.orderByName;

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

          if (angular.isUndefinedOrNull($scope.motcles)) {
            $scope.motcles = "";
          }

          // Recherche avec mot clé.
          function searchWithKey (keyword) {
            rechercheService.searchWithKey(keyword).then(function (response) {
              $scope.resultats = response;
            });
          }
          
          $scope.searchAvance = function () {
            $location.search({});
            if (!angular.isUndefinedOrNull($scope.marque) && !angular.isEmpty($scope.marque)) {
              $location.search({"marqueName":marque});
            } else {
              $scope.marque = "";
            }
            if (!angular.isUndefinedOrNull($scope.categorie) && !angular.isEmpty($scope.categorie)) {
              $location.search({"categorieName":categorie});
            } else {
              $scope.categorie = "";
            }

            if (!angular.isUndefinedOrNull($scope.ref) && !angular.isEmpty($scope.ref)) {
              $location.search({"motcles":motcles});
            } else {
              $scope.ref = "";
            }
            //if (!angular.isUndefinedOrNull($scope.ref) && !angular.isEmpty($scope.ref)) {
            //  $location.search({"categorieName":categorie});
            //} else {
            //  $scope.ref = "";
            //}

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

          if (angular.isUndefinedOrNull($scope.categorieName)) {
            $scope.categorie = "";
            searchWithKey($scope.motcles);
          } else {
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
          }
        }]);
}());