(function () {
  "use strict";

  angular.module('app').factory('rechercheService', ['$http', '$log', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $log, $cookies, EnvironmentConfig, $filter) {
    return {
      searchWithKey: function (key) {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/instrument/findwithkey", {params:{key:key}} ).then(function (response) {
          return response.data;
        }, function (errResponse) {
          $log.log("Error in AJAX call " + errResponse);
        })
      },

      searchAvance: function (marque,categorie,key) {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/instrument/findadvanced", {params: {marque:marque, categorie:categorie, key:key}}).then(function (response) {
          return response.data;
        }, function (errResponse) {
          $log.log("Error in AJAX call " + errResponse);
        })
      }
    }
  }]);
}());