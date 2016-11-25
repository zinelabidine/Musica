(function () {
  "use strict";

  angular.module('app').factory(
    'checkoutService',
      [
        '$http',
        '$log',
        '$cookies',
        'EnvironmentConfig',
        '$filter',
        function ($http, $log, $cookies, EnvironmentConfig, $filter) {
          return {
            updateClientPersonalInformation : function(data) {
              $log.log(
                '[CheckoutService] update client information'
              );
              return $http.post(
                EnvironmentConfig.GlobalBaseUrl + "/client/set/personalinfo",
                {
                  clientid : data.clientid,
                  firstname : data.firstname,
                  lastname : data.lastname,
                  address : data.address,
                  city : data.city,
                  zip : data.zip,
                  country : data.country,
                  tel : data.tel,
                  email : data.email
                },
                {params: {'Content-Type': 'application/json'}}
              );
              /*$http({
                   method: 'POST',
                   EnvironmentConfig.GlobalBaseUrl + "/client/set/personalinfo",
                   data: personalinfo,
                    headers: {
                        'Content-Type': 'application/json'
               }});*/
            }
          }
        }
      ]
  );
}());
