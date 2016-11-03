(function () {
  "use strict";

  angular.module('app')
    .controller('HomeCtrl', ['$scope', '$http', '$location', 'homeService', function ($scope, $http, $location, homeService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      var instrumentDestination = "instrument";
      $scope.promotions = [
        {
          id:1,
          src: "http://www.conceptcarz.com/images/Suzuki/suzuki-concept-kizashi-3-2008-01-800.jpg",
          alt: "",
          caption: {
            title: "Promotion 1",
            details: "Promotion 1 details"
          }
        },
        {
          id:2,
          src: "http://www.conceptcarz.com/images/Volvo/2009_Volvo_S60_Concept-Image-01-800.jpg",
          alt: "",
          caption: {
            title: "Promotion 2",
            details: "Promotion 2 details"
          }
        },
        {
          id:3,
          src: "http://www.sleepzone.ie/uploads/images/PanelImages800x400/TheBurren/General/sleepzone_hostels_burren_800x400_14.jpg",
          alt: "",
          caption: {
            title: "Promotion 3",
            details: "Promotion 3 details"
          }
        }
      ];

      $scope.goToInstrument = function (id) {
        console.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };

    }]);
}());