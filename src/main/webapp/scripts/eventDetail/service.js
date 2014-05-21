'use strict';

ipponrunningApp.factory('EventDetail', ['$resource',
    function ($resource) {
        return $resource('app/rest/events/:id/detail', {}, {
            'get': { method: 'GET'}
        });
    }]);
