(function () {
  "use strict";

  angular.module('app').factory('rechercheService', ['$http', '$log', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $log, $cookies, EnvironmentConfig, $filter) {
    return {
      getResultats: function (key) {
        return $http.get(EnvironmentConfig.GlobalBaseUrl+"/instrument/findwithkey?key=" + key).then(function (response) {
          return response.data;
        }, function (errResponse) {
          $log.log("Error in AJAX call " + errResponse);
        })
      }
    }
  }]);
}());