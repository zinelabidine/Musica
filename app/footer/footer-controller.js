(function () {
  "use strict";

  angular.module('app')
    .controller('FooterCtrl', ['$scope', '$log', '$location', 'ApplicationConfig', 'headerService', function ($scope, $log, $location, ApplicationConfig, headerService) {

      var self = this;

      $scope.categories = headerService.getCategories();

      $scope.goToCategorie = function (categorieName) {
        $location.search({});
        $location.path("/recherche").search('categorieName', categorieName);
      };

    }]);
}());