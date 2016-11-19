(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', '$log', '$location', 'headerData', 'headerService', function ($scope, $log, $location, headerData, headerService) {

      var self = this;

      self.headerData = headerData;
      $scope.categories = headerData.Categories;
      $scope.searchAction = function () {
        $location.path("/recherche/" + $scope.motcles);
        $log.log("Search with key:" + $scope.motcles);
      };

      //headerService.initHeader().then(function (response) {
      //  $log.log("initHeader-service");
      //  $scope.categories = response;
      //});

    }]);

}());