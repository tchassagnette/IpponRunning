'use strict';

ipponrunningApp.controller('EventDetailController', ['$scope', '$routeParams', 'EventDetail',
    function ($scope,  $routeParams,  EventDetail) {
	
        $scope.event = EventDetail.get({id:$routeParams.id});
    }]);
