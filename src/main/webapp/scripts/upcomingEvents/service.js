'use strict';

ipponrunningApp.factory('UpComingEvents', ['$resource',
    function ($resource) {
        return $resource('app/rest/events/upcoming', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
