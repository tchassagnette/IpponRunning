'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/registration', {
                    templateUrl: 'views/registrations.html',
                    controller: 'RegistrationController',
                    resolve:{
                        resolvedRegistration: ['Registration', function (Registration) {
                            return Registration.query();
                        }]
                    }
                })
        }]);
