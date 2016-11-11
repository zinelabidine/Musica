/**
 * Created by youness on 11/11/2016.
 */
(function () {
  "use strict";

  angular.module('app').factory('headerService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {

      initAccueil: function () {
        //return $http.get(EnvironmentConfig.GlobalBaseUrl + "/init-accueil")
        return $http.get("header/data.json")
          .then(function (response) {
            return response.data.categories;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      }
    }
  }]);
}());
