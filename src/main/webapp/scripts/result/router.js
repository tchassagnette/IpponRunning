'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/result', {
                    templateUrl: 'views/results.html',
                    controller: 'ResultController',
                    resolve:{
                        resolvedResult: ['Result', function (Result) {
                            return Result.query();
                        }]
                    }
                })
        }]);
