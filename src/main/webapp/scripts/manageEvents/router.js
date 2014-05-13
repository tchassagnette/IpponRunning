'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/manageEvents', {
                    templateUrl: 'views/manageEvents.html',
                    controller: 'ManageEventsController',
                    resolve:{
                        resolvedEvent: ['Event', function (Event) {
                            return Event.query();
                        }]
                    }
                })
        }]);
