'use strict';

ipponrunningApp.controller('ResultController', ['$scope', 'resolvedResult', 'Result',
    function ($scope, resolvedResult, Result) {

        $scope.results = resolvedResult;

        $scope.create = function () {
            Result.save($scope.result,
                function () {
                    $scope.results = Result.query();
                    $('#saveResultModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.result = Result.get({id: id});
            $('#saveResultModal').modal('show');
        };

        $scope.delete = function (id) {
            Result.delete({id: id},
                function () {
                    $scope.results = Result.query();
                });
        };

        $scope.clear = function () {
            $scope.result = {id: "", sampleTextAttribute: "", sampleDateAttribute: ""};
        };
    }]);
