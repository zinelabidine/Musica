(function () {
  "use strict";

  angular.module('app')
    .controller('HomeCtrl', ['$scope', '$http', '$log', '$location', 'ApplicationConfig', 'homeService', function ($scope, $http, $log, $location, ApplicationConfig, homeService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;

      var instrumentDestination = "instrument";
      $scope.meilleurVentes = [];
      $scope.promotions = [];
      $scope.owlCarouselMainOptions = ApplicationConfig.OWL_CAROUSEL_MAIN_OPTIONS;
      $scope.owlCarouselMeilleurVentesOptions = {
        loop: false,
        margin: 10,
        dots:true,
        pagination: true,
        autoplay: true,
        autoplaySpeed: 1000,
        autoplayHoverPause: true,
        responsiveClass:true,
        responsive:{
          0:{
            items:1
          },
          768:{
            items:2
          },
          992:{
            items:3
          },
          1200:{
            items:5
          }
        }
      };


      // Initialiser la page home
      init();

      function init() {
        homeService.initAccueil().then(function (response) {
          $log.log("Init Accueil service");
          $scope.instrumentsBestSales = response.instrumentsBestSales;
          $scope.instrumentsPromotions = response.instrumentsPromotions;
        });
      }

      $scope.goToInstrument = function (id) {
        $log.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };

    }]);
}());