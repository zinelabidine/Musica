(function () {
  "use strict";

  angular.module('app')
    .controller('RegisterCtrl', ['$scope', '$http', '$log', '$location', 'ApplicationConfig', 'registerService', function ($scope, $http, $log, $location, ApplicationConfig, registerService) {

      var self = this;
      $scope.clientInfos = {};
      $scope.clientLoginData = {};

      $scope.registerClient = function () {
        registerService.register($scope.clientInfos).then(function (response) {
          // Save informations to user story or to cookies
          $scope.$emit('authentificationEvent', { message: response });
          $location.path('home');
        }, function () {
        })
      };

      $scope.signin = function () {
        registerService.signin($scope.clientLoginData).then(function (response) {
          $scope.$emit('authentificationEvent', { message: response });
          $location.path('home');
        }, function () {
          $log.error("Je ne peux pas s'authentifier");
        })
      };

    }]);
}());