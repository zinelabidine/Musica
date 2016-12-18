(function () {
  "use strict";

  angular.module('app').factory('addinstrumentService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    
    var categories = [];
    var marques = [];

    return {
      getCategorieEtMarque:function(){
         return $http.get(EnvironmentConfig.GlobalBaseUrl + "/global/initializeheader")
          //return $http.get("header/data.json")
          .then(function (response) {
            categories = response.data.categories;
            marques = response.data.marques;
            return response.data;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      },

     	addInstrument : function(marqueLibelle,categorieLibelle,reference,quantite,prix,description,image) {
              return $http.post(
                EnvironmentConfig.GlobalBaseUrl + "/administration/create",
                {
                  marqueLibelle : marqueLibelle,
                  categorieLibelle : categorieLibelle,
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
