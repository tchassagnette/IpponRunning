'use strict';

ipponrunningApp.factory('Result', ['$resource',
    function ($resource) {
        return $resource('app/rest/results/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
