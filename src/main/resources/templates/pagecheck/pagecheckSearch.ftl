
<#include "/common/standardPage.ftl" />
<@standardPage title="Apps Portal - Portfolio"/>

   <section id="subintro">

    <div class="container">
      <div class="row">
        <div class="span4">
          <h3>Portfolio <strong>4 columns</strong></h3>
        </div>
        <div class="span8">
          <ul class="breadcrumb notop">
            <li><a href="#">Home</a><span class="divider">/</span></li>
            <li class="active">Portfolio 4 columns</li>
          </ul>
        </div>
      </div>
    </div>

  </section>

  <section id="maincontent">
    <div class="container">
      <div class="row">
        <div class="span12">
          <!-- portfolio filter -->
          <ul class="filter">
            <li class="all active"><a href="#">All categories</a></li>            
            <#list categoryList as category>
              <li class=${category.code}><a href="#">${category.description}</a></li>
			</#list>            
          </ul>
          <!-- /portolfio filter -->
        </div>
      </div>
      <div class="row">
        <ul class="portfolio-area da-thumbs">	
        </ul>
      </div>
      
      <#--  <@spring.bind "searchForm" />  -->

      <h1>Search User</h1>
          
      	<#if allErrors??>
            <div class="alert alert-danger">
                Check all errors showed.
            </div>
    	</#if>  
    	<#if msgOK??>
            <div class="alert alert-success">
               Operation success.
            </div>
    	</#if>  	
		
		<form action="<@spring.url '/Manage_Users/Search' />" method="POST">
      	<div class="row"> 
      	 <div class="col-xl-9">       
			<label class="control-label"><@spring.message 'label.user.simplefilter' /></label>
			<div class="input-group">
			 <#-- 
			 <@spring.formInput "searchForm.filterSimpleSearch" 'class="form-control"' />
             <@spring.showErrors "</br>", "form-control alert-danger" />
             -->
			    <span class="input-group-btn">
			        <button class="btn btn-info" name="submit" type="submit" value="Search">Search</button>
			    </span>
			</div>            
         </div>
     	</div>
     	</br>
		
		<div class="row"> 
	      	<div class="col-xl-12">  
		    <h2 class="sub-header">Table Users</h2>
		    <#if listBeanTable?? && listBeanTable?has_content  > 
			      <div class="table-responsive">
		            <table class="table table-striped">
		              <thead>
		                <tr>
		                  <th>Username</th>
		                  <th>Firstname</th>
		                  <th>LastName</th>
		                  <th>Role</th>
		                  <th>Actions</th>
		                </tr>
		              </thead>
		              <tbody>
		              	<#list listBeanTable as child>
						    <tr>
						      <td>${child.id}</td>
			                  <td>${child.name}</td>
			                  <td>${child.url}</td>
			                  <td>${child.lastCheck}</td>
			                  <td>
			                  	<a class="fa fa-info-circle" href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/R" title="Read"></a>
			                    <a class="fa fa-pencil-square" href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/U" title="Update"></a>
			                    <a class="fa fa-trash" href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/D" title="Delete"></a>		                  
			                  </td>
						    </tr>
					    </#list>
		              </tbody>
		            </table>
					<#-- pagination 
					<@pagination.standardPagination urlAction="/Manage_Users/Search" />
					-->	
						           	           
		          </div>
			<#else> 
			    No Details Available
			</#if>	
			</div>
		</div>	
		</form>

    </div>
  </section>
    
<#include "/common/footer.ftl">  

