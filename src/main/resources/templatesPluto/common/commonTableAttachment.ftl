

<div class="row">
<div class="col-xl-12">
<label class="control-label">Table documents</label>
<#if  baseFE.listBlobs?? &&  baseFE.listBlobs?has_content >
	<div class="table-responsive">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>File</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
          	<#list baseFE.listBlobs as child>
			    <tr>
                   <#if child.id??>
                   <@spring.formHiddenInput "baseFE.listBlobs["+child?index+"].id"/>
                   <@spring.formHiddenInput "baseFE.listBlobs["+child?index+"].filename"/>
				    <td>${child.filename}</td>
	                <td>
	                  	<button class="btn btn-info btn-sm" name="Download" type="submit" value="Download_${child.id}">
					      <span class="glyphicon glyphicon-download"></span> Download
					    </button>
					    <#if baseFE.state== "C" || baseFE.state=="U">
		                   	<button class="btn btn-danger btn-sm" name="Delete" type="submit" value="Delete_${child.id}">
						      <span class="glyphicon glyphicon-remove-circle"></span> Delete
						    </button> 
					    </#if>
                  	</td>
				   <#else>
				      <td>
				      	<@spring.formInput "baseFE.listBlobs["+child?index+"].multipartFile" 'class="form-control"' "file" />
				     	<@spring.showErrors "</br>", "form-control alert-danger" />
				      </td>
	                  <td>
                      	<button class="btn btn-primary btn-sm" name="Upload" type="submit" value="Upload">
					      <span class="glyphicon glyphicon-upload"></span> Upload
					    </button>	
	                  </td>	
				   </#if>               
			    </tr>
		    </#list>
          </tbody>
        </table>
    </div>
<#else> 
    No Details Available
</#if>
</div>
</div>
      	