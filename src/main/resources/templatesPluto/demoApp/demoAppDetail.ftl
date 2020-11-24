
<#include "/common/standardPage.ftl" />
<@standardPage title="Apps Portal - Home"/>

<@spring.bind "demoApp" />

  <section id="subintro">

    <div class="container">
      <div class="row">
        <div class="span4">
          <h3>Get <strong>in touch</strong></h3>
        </div>
        <div class="span8">
          <ul class="breadcrumb notop">
            <li><a href="#">Home</a><span class="divider">/</span></li>
            <li class="active">Contact</li>
          </ul>
        </div>
      </div>
    </div>

  </section>

  <section id="maincontent">
    <div class="container">
      <div class="row">
        <div class="span3">
          <aside>
            <div class="widget">
              <h4>Get in touch with us</h4>
              <ul>
                <li><label><strong>Phone : </strong></label>
                  <p>
                    +900 888 707 123
                  </p>
                </li>
                <li><label><strong>Email : </strong></label>
                  <p>
                    name@yoursite.com
                  </p>
                </li>
                <li><label><strong>Office address : </strong></label>
                  <p>
                    Pasar sate tusuk 22A Ave X.300 Peterongan Semarang, Indonesia
                  </p>
                </li>
              </ul>
            </div>
<!--             <div class="widget"> -->
<!--               <h4>Social network</h4> -->
<!--               <ul class="social-links"> -->
<!--                 <li><a href="#" title="Twitter"><i class="icon-bg-light icon-twitter icon-circled icon-1x active"></i></a></li> -->
<!--                 <li><a href="#" title="Facebook"><i class="icon-bg-light icon-facebook icon-circled icon-1x active"></i></a></li> -->
<!--                 <li><a href="#" title="Google plus"><i class="icon-bg-light icon-google-plus icon-circled icon-1x active"></i></a></li> -->
<!--                 <li><a href="#" title="Linkedin"><i class="icon-bg-light icon-linkedin icon-circled icon-1x active"></i></a></li> -->
<!--                 <li><a href="#" title="Pinterest"><i class="icon-bg-light icon-pinterest icon-circled icon-1x active"></i></a></li> -->
<!--               </ul> -->
<!--             </div> -->
          </aside>
        </div>
        <div class="span9">
<!--           <div class="map-container"> -->
<!--             <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22864.11283411948!2d-73.96468908098944!3d40.630720240038435!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew+York%2C+NY%2C+USA!5e0!3m2!1sen!2sbg!4v1540447494452" width="100%" height="380" frameborder="0" style="border:0" allowfullscreen></iframe> -->
<!--           </div> -->
<!--           <div class="spacer30"> -->
<!--           </div> -->

          <div id="sendmessage">Your message has been sent. Thank you!</div>
          <div id="errormessage"></div>
          <form action="/portal/portfolio/new" method="post" role="form" class="contactForm" >
            <div class="row">
              <div class="span3 form-group">
                <input type="text" name="code" class="form-control" id="code" placeholder="code" 
                	data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                <div class="validation"></div>
              </div>
              
              <div class="span3 form-group">
                <input type="text" class="form-control" name="name" id="name" placeholder="name" 
                data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                <div class="validation"></div>
              </div>              

              <div class="span3 form-group">
				<@spring.formSingleSelect "demoApp.codeCategory.id", categoryList, 'class="form-control"'/>
                <div class="validation"></div>
              </div>
              
              <div class="span9 form-group"></div>
              
              
             <div class="span3 form-group">
                <input type="text" class="form-control" name="link" id="link" placeholder="link" 
                data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                <div class="validation"></div>
              </div>   
              
              
              <div class="span9 form-group">
                <textarea class="form-control" name="briefDescription" rows="5" data-rule="required" 
                data-msg="Please write something for us" placeholder="briefDescription"></textarea>
                <div class="validation"></div>
              </div>
              
              <div class="span9 form-group">
                <textarea class="form-control" name="description" rows="5" data-rule="required" 
                data-msg="Please write something for us" placeholder="description"></textarea>
                <div class="validation"></div>
              </div>
              
              <div class="span9 form-group">
                 <hr>
               </div>

               <div class="span9 form-group">
                 <button class="btn btn-color btn-rounded" type="submit">Send message</button>
               </div>
                   
              <#--          
              <div class="span9 form-group">
              	<#include "../common/commonTableAttachment.ftl"  parse=true>
              </div>
              -->
              
             </div>              
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
  

<#include "/common/footer.ftl">  

