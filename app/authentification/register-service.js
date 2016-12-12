(function () {
  "use strict";

  angular.module('app').factory('registerService', ['$http', '$log', "$q", '$cookies', 'EnvironmentConfig', 'globalService', '$filter', function ($http, $log, $q, $cookies, EnvironmentConfig, globalService, $filter) {

    function dateAdd(date, interval, units) {
      var ret = new Date(date);
      switch (interval.toLowerCase()) {
        case 'year'   :
          ret.setFullYear(ret.getFullYear() + units);
          break;
        case 'quarter':
          ret.setMonth(ret.getMonth() + 3 * units);
          break;
        case 'month'  :
          ret.setMonth(ret.getMonth() + units);
          break;
        case 'week'   :
          ret.setDate(ret.getDate() + 7 * units);
          break;
        case 'day'    :
          ret.setDate(ret.getDate() + units);
          break;
        case 'hour'   :
          ret.setTime(ret.getTime() + units * 3600000);
          break;
        case 'minute' :
          ret.setTime(ret.getTime() + units * 60000);
          break;
        case 'second' :
          ret.setTime(ret.getTime() + units * 1000);
          break;
        default       :
          ret = undefined;
          break;
      }
      return ret;
    }

    return {

      signin: function (clientLoginData) {

        var deferred = $q.defer();
        globalService.cleanSessionStorage();
        globalService.login(clientLoginData.login);
        var basicToken = clientLoginData.login + ':' + clientLoginData.mdp;
        var tokenBase64 = window.btoa(unescape(encodeURIComponent(basicToken)));

        $http({
          url: EnvironmentConfig.GlobalBaseUrl + "/auth/login",
          method: "POST",
          headers: {
            'Authorization': 'Basic ' + tokenBase64,
            'Content-Type': 'application/json; charset=utf-8'
          },
          data: clientLoginData
        }).success(function (data, status, headers, config) {

            globalService.token(tokenBase64);
            // use new Date().getTime()
            globalService.expirationDate(dateAdd(new Date, 'hour', 1).getTime());

            // Store personal data
            data.userId = clientLoginData.login;
            console.log(data);
            globalService.personalDatas(JSON.stringify(data));

            deferred.resolve(data, status);
          })
          .error(function (data, status, headers, config, statusText) {
            globalService.cleanSessionStorage();
            console.log(status);
            var failedDetail = {
              "status": status,
              "reason": data.reason
            };
            return deferred.reject(failedDetail);
          });
        return deferred.promise;
      },
      logout: function () {
        globalService.cleanSessionStorage();
      }

      ,
      register: function (clientRegisterData) {

        var deferred = $q.defer();
        globalService.cleanSessionStorage();
        globalService.login(clientRegisterData.login);
        var basicToken = clientRegisterData.login + ':' + clientRegisterData.mdp;
        var tokenBase64 = window.btoa(unescape(encodeURIComponent(basicToken)));

        $http({
          url: EnvironmentConfig.GlobalBaseUrl + "/auth/register",
          method: "POST",
          headers: {
            'Content-Type': 'application/json; charset=utf-8'
          },
          data: clientRegisterData
        }).success(function (data, status, headers, config) {

            globalService.token(tokenBase64);
            // use new Date().getTime()
            globalService.expirationDate(dateAdd(new Date, 'hour', 1).getTime());

            // Store personal data
            data.userId = clientRegisterData.login;
            console.log(data);
            globalService.personalDatas(JSON.stringify(data));

            deferred.resolve(data, status);
          })
          .error(function (data, status, headers, config, statusText) {
            globalService.cleanSessionStorage();
            console.log(status);
            var failedDetail = {
              "status": status,
              "reason": data.reason
            };
            return deferred.reject(failedDetail);
          });
        return deferred.promise;
      },
      isConnected: function () {
        var accessToken = globalService.token();
        if (globalService.expirationDate() == null || globalService.expirationDate() == undefined) {
          return false;
        }
        var expDate = new Date(parseInt(globalService.expirationDate()));
        var now = new Date();
        var isTokenStillValid = (expDate.getTime() - now.getTime()) > 0;
        return (accessToken != null && accessToken != undefined && isTokenStillValid);
      }

    }
  }
  ]);
}());
