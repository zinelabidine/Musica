(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', '$log', 'headerService', function ($scope, $log, headerService) {

      var self = this;
      headerService.initAccueil().then(function (response) {
        $log.log("initAccueil-service");
        $scope.categories = response;
      });

    }]);

}());