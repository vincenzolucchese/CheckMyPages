
<#include "/common/standardPage.ftl" />
<@standardPage title="Apps Portal - Portfolio"/> <#-- <@spring.bind
"searchForm" /> -->


<h1>Search User</h1>


<form action="<@spring.url '/Manage_Users/Search' />" method="POST">
	<div class="row">
		<div class="col-xl-9">
			<label class="control-label"><@spring.message
				'label.user.simplefilter' /></label>
			<div class="input-group">
				<#-- <@spring.formInput "searchForm.filterSimpleSearch"
				'class="form-control"' /> <@spring.showErrors "</br>", "form-control
				alert-danger" /> --> <span class="input-group-btn">
					<button class="btn btn-info" name="submit" type="submit"
						value="Search">Search</button>
				</span>
			</div>
		</div>
	</div>
	</br>

	<h2>Section title</h2>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
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
					<td><a class="fa fa-info-circle"
						href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/R"
						title="Read"></a> <a class="fa fa-pencil-square"
						href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/U"
						title="Update"></a> <a class="fa fa-trash"
						href="<@spring.url '/Manage_Users/InsertUpdateViewDelete/'/>${child.id?string.computer}/D"
						title="Delete"></a></td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</form>

<#include"/common/footer.ftl">