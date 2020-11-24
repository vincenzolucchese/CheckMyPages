<#include "/common/standardPage.ftl" />
<@standardPage title="Apps Portal - Home"/>

<@spring.bind "demoApp" />
  <section id="subintro">

    <div class="container">
      <div class="row">
        <div class="span4">
          <h3><strong>${demoApp.name}</strong></h3>
        </div>
        <div class="span8">
          <ul class="breadcrumb notop">
            <li><a href="#">Home</a><span class="divider">/</span></li>
            <li class="active">Portfolio detail</li>
          </ul>
        </div>
      </div>
    </div>

  </section>

  <section id="maincontent">
    <div class="container">
      <div class="row">
        <div class="span12">
          <article>
            <div class="heading">
              <h3>App name: <strong>${demoApp.name} </strong>&nbsp; code: <strong>${demoApp.code} </strong></h3>
            </div>
            <div class="clearfix">
            </div>
            <div class="row">
              <div class="span4">
                <aside>
                  <div class="widget">
                  <form action="<@spring.url '/portfolio/start'/>" method="post" role="form" class="contactForm" >
                    <h4>Project information</h4>
                    <ul class="project-detail">
                      <li><label>Project name :</label>${demoApp.name}</li>
                      <li><label>Category :</label>${demoApp.codeCategory.description}</li>
                      <li><label>Project date :</label> 12 June 2013</li>
                      <li><label>Project link :</label><a href=${demoApp.link}>${demoApp.link}</a></li>
                      <li><label>Start :</label><a href="<@spring.url '/portfolio/start?code=${demoApp.code}'/>">portfolio/start</a></li>
                      <li><label>Stop :</label><a href="<@spring.url '/portfolio/stop?code=${demoApp.code}'/>">portfolio/stop</a></li>
                    </ul>
                    
	                   <div class="form-group">
		                 <button class="btn btn-color btn-rounded" type="submit">Send message</button>
		               </div>
	               </form>
	               
                  </div>
                </aside>
              </div>
            
              <div class="span8">
                <!-- start flexslider -->
                <div class="flexslider">
                  <ul class="slides">
                    <li>
                      <img src="<@spring.url '/img/slides/portfolio/img1.jpg'/>" alt="" />
                    </li>
                    <li>
                      <img src="<@spring.url '/img/slides/portfolio/img2.jpg'/>" alt="" />
                    </li>
                    <li>
                      <img src="<@spring.url '/img/slides/portfolio/img3.jpg'/>" alt="" />
                    </li>
                  </ul>
                </div>
                <!-- end flexslider -->
                <div class="blank10"></div>
                <p>${demoApp.description}</p>
              </div>
			</div>
          </article>
          <!-- end article full post -->
        </div>
      </div>
    </div>
  </section>

<#include "/common/footer.ftl">  
