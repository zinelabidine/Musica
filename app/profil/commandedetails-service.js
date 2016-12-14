(function () {
  "use strict";

  angular.module('app').factory('commandedetailsService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {
    	getCommandeDetails: function (commandeId) {
	        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/commande/getcommande/"+commandeId)
	          .then(function (response) {
	            	return response.data;
	          }, function (errResponse) {
	            $log.log("Error in AJAX call " + errResponse);
	          })
	      	}
    }
  }]);
}());
