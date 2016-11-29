/**
 * Created by youness on 11/11/2016.
 */
(function () {
  "use strict";

  angular.module('app').factory('headerService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {

    var categories = [];

    return {
      getCategories: function () {
        return categories;
      },

      setCategories: function (categoriesList) {
        categories = categoriesList;
      },

      initHeader: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/global/initializeheader")
          //return $http.get("header/data.json")
          .then(function (response) {
            categories = response.data.Categories;
            return response.data;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      },

      initCartSize: function (client) {
        console.log("initCartSize");
        return $http.get(
          EnvironmentConfig.GlobalBaseUrl + "/panier/paniersize/"+client,
          {params: {}}
        );
      }
    }
  }]);
}());
