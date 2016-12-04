(function () {
  "use strict";

  angular.module('app').factory('addinstrumentService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {
     	addInstrument : function(marqueId,categorieId,reference,quantite,prix,description,image) {
              return $http.post(
                EnvironmentConfig.GlobalBaseUrl + "/administration/create",
                {
                  marqueId : marqueId,
                  categorieId : categorieId,
                  reference : reference,
                  quantite : quantite,
                  prix : prix,
                  description : description,
                  image : image,
                },
                {params: {'Content-Type': 'application/json'}}
              );
            },
    }
  }]);
}());
