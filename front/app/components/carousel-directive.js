(function () {
  "use strict";

  angular.module('app')
    .directive('carousel', function ($timeout) {
      return {
        restrict: 'E',
        scope: {
          links: '=',
          goTo: '&'
        },
        templateUrl: 'components/carousel-directive.html',
        link: function (scope, element) {
          scope.goToLocal = function (id) {
            scope.goTo({
              'id': id
            });
          };
          $timeout(function () {
            $('.carousel-indicators li', element).first().addClass('active');
            $('.carousel-inner .item', element).first().addClass('active');
          });
        }
      }
    });
}());