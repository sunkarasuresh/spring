'use strict';

angular.module('bookApp').factory('BookService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllBooks: loadAllBooks,
                getAllBooks: getAllBooks,
                getBook: getBook,
                createBook: createBook,
                updateBook: updateBook,
                countUpdate: countUpdate,
                removeBook: removeBook
            };

            return factory;

            function loadAllBooks() {
                console.log('Fetching all Books');
                var deferred = $q.defer();
                $http.get(urls.Book_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Books');
                            $localStorage.Books = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Books');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllBooks(){
                return $localStorage.Books;
            }

            function getBook(id) {
                console.log('Fetching Book with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.Book_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Book with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createBook(Book) {
                console.log('Creating Book');
                var deferred = $q.defer();
                $http.post(urls.Book_SERVICE_API, Book)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Book : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateBook(Book, id) {
                console.log('Updating Book with id '+id);
                var deferred = $q.defer();
                $http.put(urls.Book_SERVICE_API + id, Book)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function countUpdate(id,txType) {
                console.log('Updating Book count with id '+id+" Tx type "+txType);
                var deferred = $q.defer();
                $http.put(urls.Book_SERVICE_API + id+"/"+txType)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Book with id :'+id+" tx type "+txType);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            
            function removeBook(id) {
                console.log('Removing Book with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.Book_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllBooks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Book with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);