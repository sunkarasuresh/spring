<div class="container">
   <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="alert alert-success" role="alert" ng-if="ctrl.txMessage">{{ctrl.txMessage}}</div>
        <div class="alert alert-danger" role="alert" ng-if="ctrl.txErrorMessage">{{ctrl.txErrorMessage}}</div>
        <div class="panel-heading"><span class="lead">List of Books </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>Book Name</th>
		                <th>Auther Name</th>
		                <th>ISBN Code</th>
		                <th># of Books</th>
		                <th># of Books Issued</th>
		                <th>Publish Date</th>
		                <th>Book Category</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllBooks()" ng-class="{'selected': u.id== idSelected}">
		                <td>{{u.id}}</td>
		                <td>{{u.bookName}}</td>
		                <td>{{u.autherName}}</td>
		                <td>{{u.isbnCode}}</td>
		                <td>{{u.noOfBooks}}</td>
		                <td>{{u.booksStock}}</td>
		                <td>{{u.publichDate | date:'yyyy-MM-dd HH:mm:ss'}}</td>
		                <td>{{u.bookCategory}}</td>
		                <td><button type="button" ng-click="ctrl.editBook(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeBook(u.id)" ng-disabled="{{!u.booksStock == 0}}" class="btn btn-danger custom-width">Delete</button></td>
		                <td><button type="button" ng-click="ctrl.countUpdate(u.id,'Issue')" class="btn btn-primary custom-width">Issue</button></td>
		                <td><button type="button" ng-click="ctrl.countUpdate(u.id,'Return')" class="btn btn-primary custom-width">Return</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
    <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Book </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.Book.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bookName">Book Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Book.bookName" id="bookName" class="Bookname form-control input-sm" placeholder="Enter Book Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="autherName">Auther Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Book.autherName" id="autherName" class="form-control input-sm" placeholder="Enter Auther Name." required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                
                    <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="isbnCode">isbnCode</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Book.isbnCode" id="isbnCode" class="form-control input-sm" placeholder="Enter ISBN Code." required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
                   <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="noOfBooks"># Of Books</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Book.noOfBooks" id="noOfBooks" class="form-control input-sm" placeholder="Enter # of Books." required ng-pattern="onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
				<!-- 	<div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="booksStock">Transaction Type</label>
	                        <div class="col-md-7">
	                        	<select id="booksStock" class="form-control" ng-model="ctrl.Book.booksStock"  placeholder="Select Transaction type" ng-options="i.id as i.name for i in transactionsList">
	                        	<option value="" selected="selected">New / update</option>
	                        	</select>
	                        </div>
	                    </div>
	                </div> -->
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bookCategory">Book Category</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.Book.bookCategory" id="bookCategory" class="form-control input-sm" placeholder="Enter Book Category." required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.Book.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
</div>