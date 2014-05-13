'use strict';

ipponrunningApp.factory('Registration', ['$resource',
    function ($resource) {
        return $resource('app/rest/registrations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    }]);
