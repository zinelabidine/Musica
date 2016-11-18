(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', function ($scope) {

      var self = this;
      $log.log("Mot:cle: "+ $scope.motcles);
      //$scope.isCollapsed = true;
      //$scope.$on('$routeChangeSuccess', function () {
      //  $scope.isCollapsed = true;
      //});

    }]);

}());
