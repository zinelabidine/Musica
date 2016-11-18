(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', '$log','$location','headerService', function ($scope, $log,$location,headerService) {

      var self = this;

      $scope.searchAction = function() { 
        $location.path("/recherche/" + $scope.motcles);           
        $log.log("Search with key:" + $scope.motcles);    
      }

      headerService.initHeader().then(function (response) {
        $log.log("initHeader-service");
        $scope.categories = response;
      });

    }]);

}());