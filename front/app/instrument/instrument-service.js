/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app').factory('instrumentService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {

      getDetailsInstrument: function (instrumentId) {
        // return $http.get("instrument/data.json")
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/instrument/findwithid", {params: {id: instrumentId}})
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      }
    }
  }]);
}());
