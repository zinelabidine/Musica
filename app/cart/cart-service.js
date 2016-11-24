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
            },

            deleteInstrumentPanier : function(cartId, instrumentId) {
              $log.log(
                '[cartService] Delete instrument '
                + instrumentId
                + ' from cart '
                + cartId
              );
              return $http.delete(
                EnvironmentConfig.GlobalBaseUrl + "/panier/supprimer/"+instrumentId,
                {params: {}}
              );
            },

            updateCartInstrumentQuantity : function(instrumentId, quantite) {
              $log.log(
                '[cartService] Update instrument '
                + instrumentId
                + ' quantite to '
                + quantite
              );
              return $http.get(
                EnvironmentConfig.GlobalBaseUrl + "/panier/modifierligne/"+instrumentId+'/'+quantite,
                {params: {}}
              )
            }
          }
        }
      ]
  );
}());
