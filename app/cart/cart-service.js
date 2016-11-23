(function () {
  "use strict";

  angular.module('app').factory(
    'cartService',
      [
        '$http',
        '$log',
        '$cookies',
        'EnvironmentConfig',
        '$filter',
        function ($http, $log, $cookies, EnvironmentConfig, $filter) {
          return {
            getResultats: function (client) {
              return $http.get(
                EnvironmentConfig.GlobalBaseUrl + "/panier/getpanier/"+client,
                {params: {}}
              ).then(function (response) {
                return response.data;
              }, function (errResponse) {
                $log.log("Error in AJAX call " + errResponse);
              })
            }
          }
        }
      ]
  );
}());
