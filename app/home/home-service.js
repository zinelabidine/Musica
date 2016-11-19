(function () {
  "use strict";

  angular.module('app').factory('homeService', ['$http', '$log', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $log, $cookies, EnvironmentConfig, $filter) {
    return {

      initAccueil: function () {

        // return $http.get("home/data.json")
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/accueil/initializehomecontent")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      },

      getMeilleurVente: function () {
        //return $http.get(EnvironmentConfig.GlobalBaseUrl + "/test/meilleur-vente")
        return $http.get("home/data.json")
          .then(function (response) {
            return response.data.meilleurVentes;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          });
      },

      getPromotions: function () {
        return $http.get("home/data.json")
          .then(function (response) {
            return response.data.promotions;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          });
      }
    }
  }]);
}());
