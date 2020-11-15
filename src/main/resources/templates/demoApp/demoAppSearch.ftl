
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
        
        <#list demoAppList as demoApp>        
          <li class="portfolio-item" data-id="id-0" data-type=${demoApp.codeCategory.code}>
            <div class="span3">
              <div class="thumbnail">
                <div class="image-wrapp">
                  <img src="<@spring.url '/img/portfolio/img-1.jpg'/>" alt=${demoApp.name} title=${demoApp.name} />
                  <article class="da-animate da-slideFromRight">
                    <a class="zoom" data-pretty="prettyPhoto" href="<@spring.url '/img/portfolio/big-1.jpg'/>">
							<i class="icon-bg-light icon-zoom-in icon-circled icon-2x"></i>
							</a>
                    <a href="<@spring.url '/portfolio/detail?code=${demoApp.code}'/>">
							<i class="icon-bg-light icon-link icon-circled icon-2x"></i>
							</a>
                  </article>
                </div>
                <div class="desc">
                  <h5><i class="icon-file"></i> <strong>${demoApp.name}</strong></h5>
                  <p>${demoApp.briefDescription}</p>
                </div>
              </div>
            </div>
          </li>                        
		</#list> 
		
        </ul>
      </div>

    </div>
  </section>
    
<#include "/common/footer.ftl">  

