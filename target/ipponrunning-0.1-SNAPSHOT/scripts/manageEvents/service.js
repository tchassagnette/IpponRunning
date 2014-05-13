'use strict';

ipponrunningApp.factory('Event', ['$resource',
    function ($resource) {
        return $resource('app/rest/events/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
