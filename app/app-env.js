angular.module('app.env', [])
  .constant('EnvironmentConfig', {
    "GlobalBaseUrl": "http://localhost:8080/MusicaServerWSRS/app",
    "defaultTimeZone": "Europe/paris",
    "defaultDateFormat": "DD-MM-YYYY HH:mm"
  });
