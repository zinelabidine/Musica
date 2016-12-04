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
            getClient: function (client) {
              return $http.get(
                EnvironmentConfig.GlobalBaseUrl + "/client/get?id="+client,
                {params: {}}
              ).then(function (response) {
                return response.data;
              }, function (errResponse) {
                $log.log("Error in AJAX call " + errResponse);
              })
            },

            updateClientPersonalInformation : function(data) {
              $log.log(
                '[CheckoutService] update client information'
              );
              return $http.post(
                EnvironmentConfig.GlobalBaseUrl + "/client/set/personalinfo",
                {
                  utilisateurid : data.utilisateurid,
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
            },

            updateClientPaymentInformation : function(data) {
              $log.log(
                '[CheckoutService] update payment client information'
              );
              return $http.post(
                EnvironmentConfig.GlobalBaseUrl + "/client/set/paymentinfo",
                {
                  utilisateurid : data.utilisateurid,
                  cardname : data.cardname,
                  cardnumber : data.cardnumber,
                  cardmonth : data.cardmonth,
                  cardyear : data.cardyear
                },
                {params: {'Content-Type': 'application/json'}}
              );
            },

            getCommande: function(id) {
              console.log(id);
              return $http.get(
                EnvironmentConfig.GlobalBaseUrl + "/commande/getcommande/" + id,
                {params: {}}
              );
            }
          }
        }
      ]
  );
}());
