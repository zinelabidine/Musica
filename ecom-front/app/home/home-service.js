(function () {
  "use strict";

  angular.module('app').factory('homeService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {

      /**
       *  Retrieve Trips
       */
      getLastTrips: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/trip/lastTrips/")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      },

      getLastTrip: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/trip/lastTrip/")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      }

    }
  }]);
}());
