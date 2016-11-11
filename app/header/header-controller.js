(function () {
  "use strict";

  angular.module('app')
    .controller('HeaderCtrl', ['$scope', '$log', 'headerService', function ($scope, $log, headerService) {

      var self = this;
      headerService.initHeader().then(function (response) {
        $log.log("initHeader-service");
        $scope.categories = response;
      });

    }]);

}());