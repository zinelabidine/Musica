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
            getCart: function (client) {
              return $http.get(EnvironmentConfig.GlobalBaseUrl + "/panier/getpanier/"+client).then(function (response) {
                return response.data;
              }, function (errResponse) {
                $log.log("Error in AJAX call " + errResponse);
              })
            },

            getCartSize: function (client) {
              return $http.get(EnvironmentConfig.GlobalBaseUrl + "/panier/paniersize/"+client).then(function (response) {
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
              return $http.delete(EnvironmentConfig.GlobalBaseUrl + "/panier/supprimer/"+instrumentId);
            },

            updateCartInstrumentQuantity : function(instrumentId, quantite) {
              $log.log(
                '[cartService] Update instrument '
                + instrumentId
                + ' quantite to '
                + quantite
              );
              return $http.get(EnvironmentConfig.GlobalBaseUrl + "/panier/modifierligne/"+instrumentId+'/'+quantite)
            },

            validateCart : function(utilisateurid, panierid) {
              $log.log(
                '[cartService] Validate cart ' + panierid + ' of client ' + utilisateurid
              );

              return $http.get(
                EnvironmentConfig.GlobalBaseUrl
                  + "/panier/valider/"+utilisateurid+'/'+panierid
              )

            },

            finaliseCommande : function(utilisateurid) {
              $log.log('[cartService] Finalise commande ' + utilisateurid);
              return $http.get(
                EnvironmentConfig.GlobalBaseUrl +
                  "/panier/payer/"+utilisateurid
              );
            }
          }
        }
      ]
  );
}());
