(function () {
  "use strict";

  angular.module('app').factory('clientcommandeService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {
    	getCommande: function (utilisateurid) {
	        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/commande/getcommandesbyutilisateur/"+utilisateurid)
	          .then(function (response) {
	            	return response.data;
	          }, function (errResponse) {
	            $log.log("Error in AJAX call " + errResponse);
	          })
	      	}
    }
  }]);
}());
