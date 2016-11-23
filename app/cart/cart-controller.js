(function () {
  "use strict";

  angular.module('app')
    .controller('CartCtrl',
      [
        '$scope',
        '$stateParams',
        '$http',
        '$log',
        '$location',
        'ApplicationConfig',
        'cartService',
        function (
          $scope,$stateParams, $http, $log, $location, ApplicationConfig, cartService
        ) {
            // The content of the controller.
            // Instead of using this use the variable self.
            var self = this;

            $scope.resultats =[];
            $scope.resultats =[];
            $scope.motcles = $stateParams.motcles;
            $scope.orderByReverse = false;

            search();

            function search() {
              $log.log("client:" + $scope.motcles);
              cartService.getResultats($scope.motcles).then(function (response) {
                  $scope.resultats = response;
                  $log.log($scope.resultats);
                });
            }

    }]);
}());
