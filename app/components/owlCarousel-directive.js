/**
 * Created by youness on 03/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .directive('wrapOwlcarousel', function () {
      return {
        restrict: 'E',
        transclude: false,
        link: function (scope, element, attrs) {
          scope.initCarousel = function (element) {
            // provide any default options you want
            var defaultOptions = {};
            var customOptions = scope.$eval($(element).attr('data-options'));
            // combine the two options objects
            for (var key in customOptions) {
              defaultOptions[key] = customOptions[key];
            }
            // init carousel
            var curOwl = $(element).data('owlCarousel');
            if (!angular.isDefined(curOwl)) {
              $(element).owlCarousel(defaultOptions);
            }
            scope.cnt++;
          };
        }
      };
    }).directive('owlCarouselItem', [function () {
    return {
      restrict: 'A',
      transclude: false,
      link: function (scope, element) {
        // wait for the last item in the ng-repeat then call init
        if (scope.$last) {
          scope.initCarousel(element.parent());
        }
      }
    };
  }]);
}());