(function () {
  "use strict";

  angular.module('app')
    .controller('RegisterCtrl', ['$scope', '$http', '$log', '$location', '$stateParams','ApplicationConfig', 'registerService', function ($scope, $http, $log, $location, $stateParams, ApplicationConfig, registerService) {

      var self = this;
      $scope.clientInfos = {};
      $scope.clientLoginData = {};
      $scope.switch_url = $stateParams.switch_url;

      $scope.registerClient = function () {
        registerService.register($scope.clientInfos).then(function (response) {
          // Save informations to user story or to cookies
          $scope.$emit('authentificationEvent', { message: response });
          $scope.$emit('cartInstrumentChanged');
          if(!angular.isUndefinedOrNull($scope.switch_url)){
            $location.search({});
            $location.path($scope.switch_url);
          } else
            $location.path('home');
        }, function (error) {
        })
      };

      $scope.signin = function () {
        registerService.signin($scope.clientLoginData).then(function (response) {
          $scope.$emit('authentificationEvent', { message: response });
          $scope.$emit('cartInstrumentChanged');

          if(!angular.isUndefinedOrNull($scope.switch_url)){
            $location.search({});
            $location.path($scope.switch_url);
          } else
            $location.path('home');
        }, function () {
          $log.error("Je ne peux pas s'authentifier");
        })
      };

    }]);
}());