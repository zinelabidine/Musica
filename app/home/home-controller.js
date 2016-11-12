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
        items: 5,
        loop: false,
        margin: 10,
        dots:true,
        pagination: true,
        autoplay: true,
        autoplaySpeed: 1000,
        autoplayHoverPause: true
      };


      // Initialiser la page home
      init();

      function init() {
        homeService.initAccueil().then(function (response) {
          $log.log("Init Accueil service");
          $scope.meilleurVentes = response.meilleurVentes;
          $scope.promotions = response.promotions;
          $log.log($scope.promotions);
        });
      }

      $scope.goToInstrument = function (id) {
        $log.log("goToInstrument");
        $location.path(instrumentDestination + '/' + id);
      };

    }]);
}());