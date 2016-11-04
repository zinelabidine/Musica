(function () {
  "use strict";

  angular.module('app').factory('homeService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {

      /**
       *  Retrieve Trips
       */
      getLastTrips: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/trip/lastTrips/")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            console.log('Error in AJAX call :' + errResponse);
          });
      },

      getPromotions: function () {
        return [
          {
            id: 1,
            src: "./img/baterie1.png",
            alt: "",
            caption: {
              title: "BATTERIE ACOUSTIQUE ROCK PEARL",
              details: "758,00 €"
            }
          },
          {
            id: 2,
            src: "./img/guitar1.png",
            alt: "",
            caption: {
              title: "EPIPHONE DR-100 EBONY CHROME HARDWARE",
              details: "129,00 €"
            }
          },
          {
            id: 3,
            src: "./img/guitar2.png",
            alt: "",
            caption: {
              title: "EPIPHONE HUMMINGBIRD PRO E-A FADED",
              details: "289,00 €"
            }
          },
          {
            id: 4,
            src: "./img/neumann1.jpg",
            alt: "",
            caption: {
              title: "NEUMANN KH120A",
              details: "597,00 €"
            }
          },
          {
            id: 5,
            src: "./img/pedale1.png",
            alt: "",
            caption: {
              title: "PEDALE LOOPER BOSS RC30",
              details: "199,00 €"
            }
          }
        ];
      },

      getBestSales: function () {
        return [
          {
            id: 1,
            src: {
              front: "./img/baterie1.png",
              back: "./img/baterie1.png"
            },
            alt: "",
            caption: {
              title: "BATTERIE ACOUSTIQUE ROCK PEARL",
              price: "758,00 €"
            }
          },
          {
            id: 2,
            src: {
              front: "./img/guitar1.png",
              back: "./img/guitar1.png"
            },
            alt: "",
            caption: {
              title: "EPIPHONE DR-100 EBONY CHROME HARDWARE",
              price: "129,00 €"
            }
          },
          {
            id: 3,
            src: {
              front: "./img/guitar2.png",
              back: "./img/guitar2.png"
            },
            alt: "",
            caption: {
              title: "EPIPHONE HUMMINGBIRD PRO E-A FADED",
              price: "289,00 €"
            }
          },
          {
            id: 4,
            src: {
              front: "./img/neumann1.jpg",
              back: "./img/neumann1.jpg"
            },
            alt: "",
            caption: {
              title: "NEUMANN KH120A",
              price: "597,00 €"
            }
          },
          {
            id: 5,
            src: {
              front: "./img/pedale1.png",
              back: "./img/pedale1.png"
            },
            alt: "",
            caption: {
              title: "PEDALE LOOPER BOSS RC30",
              price: "199,00 €"
            }
          }
        ];
      }

    }
  }]);
}());
