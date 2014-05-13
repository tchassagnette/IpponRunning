'use strict';

ipponrunningApp.controller('UpComingEventsController', ['$scope', 'resolvedEvent', 'Event',
    function ($scope, resolvedEvent, Event) {

        $scope.events = resolvedEvent;
}]);