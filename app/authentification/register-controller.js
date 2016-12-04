(function () {
  "use strict";

  angular.module('app')
    .controller('RegisterCtrl', ['$scope', '$http', '$log', '$location', 'ApplicationConfig', 'registerService', function ($scope, $http, $log, $location, ApplicationConfig, registerService) {

      var self = this;
      $scope.clientInfos = {};

      $scope.registerClient = function () {
        registerService.register($scope.clientInfos).then(function (response) {

          // Save informations to user story or to cookies


          $location.path('home');
        }, function () {

        })
      };

    }]);
}());