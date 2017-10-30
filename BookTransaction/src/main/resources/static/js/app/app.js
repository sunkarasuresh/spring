var app = angular.module('bookApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/BookTransaction',
    Book_SERVICE_API : 'http://localhost:8080/BookTransaction/api/Book/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/booksDetails.',
                controller:'BookController',
                controllerAs:'ctrl',
                resolve: {
                    Books: function ($q, BookService) {
                        console.log('Load all Books');
                        var deferred = $q.defer();
                        BookService.loadAllBooks().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

