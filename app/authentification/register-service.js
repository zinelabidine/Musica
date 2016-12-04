(function () {
  "use strict";

  angular.module('app').factory('registerService', ['$http', '$log', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $log, $cookies, EnvironmentConfig, $filter) {
    return {

      login: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/accueil/initializehomecontent")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      },
      logout: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/accueil/initializehomecontent")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      },
      register: function (data) {
        return $http.post(EnvironmentConfig.GlobalBaseUrl + "/auth/register", data)
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      }

    }
  }]);
}());
