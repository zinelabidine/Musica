/**
 * Created by youness on 04/11/2016.
 */
(function () {
  "use strict";

  angular.module('app').factory('instrumentService', ['$http', '$cookies', 'EnvironmentConfig', '$filter', function ($http, $cookies, EnvironmentConfig, $filter) {
    return {

      getDetailsInstrument: function () {
        return {
          ref: "PPA EXX725SC-702",
          images: ["./img/baterie1.png", "./img/baterie2.png", "./img/baterie3.jpg"],
          titre: "Batterie acoustique rock pearl",
          prix: "758,00 €",
          marque:" - ",
          musicien:" - ",
          categorie: "baterie",
          description : "Après plusieurs années d'absence, la batterie la plus vendue de l'histoire fait son grand retour. La mythique Pearl Export offre toujours le même rapport qualité/prix et le son qui l'ont rendue célèbre mais cette nouvelle version apporte une multitude de nouveautés dont une pédale Demonator et un pa"
        };
      }
    }
  }]);
}());
