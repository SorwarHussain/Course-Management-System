<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome! Contact Page</title>
<link rel="stylesheet" href="css/contact.css" rel="stylesheet"/>
<link
rel="stylesheet"
href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
crossorigin="anonymous"
/>
</head>
<body>
<%@ include file = "header.jsp" %> 
<div Style="text-align:center;padding-top:130px;">
</div>
 <!--heading-->
    <div class="contact_heading">
      <h1>Contact Us</h1>
    </div>
    <!--end of heading-->
    <!--contact page email phone-->
    <div class="contact_e_p">
      <div class="contact_e">
        <a href="https://mail.google.com/mail/u/0/#inbox?compose=CllgCHrjFRQmpCnjmfkwcXxRpNcmwVzvskdnThqWwPBJmKNFGbqLqFDbJFcWGdTmBvCrTSrPcQV">
        <p><i class="fas fa-envelope"></i></p></a>
        <div class="contact_e_h">
        <h1>Email:</h1>
        <h4>sorwarsust98@gmail.com</h4>
        </div>
      </div>
      <div class="contact_p">
        <p><i class="fas fa-phone-alt"></i></p>
        <div class="contact_p_h">
        <h1>Call:</h1>
        <h4>+01303-592247</h4>
        </div>
      </div>
    </div>

    <!--end of contact_email_phone div-->
    <!--start contact page form-->
    <div class="contact_form">
      <form method="post" action="Contact" >
        <fieldset>
          <legend>Name <sup>*</sup>
          </legend>
          <input type="text" name="name" /><br />
        </fieldset>
        <fieldset>
          <legend>Email<sup>*</sup>
          </legend>
          <input type="email" name="email" /><br />
        </fieldset>
        <fieldset>
          <legend>Subject <sup>*</sup>
          </legend>
          <input type="text" name="subject"  /><br />
        </fieldset>
        <fieldset>
          <legend>Message <sup>*</sup>
          </legend>
          <textarea  name="message" rows="5" cols="30" placeholder="Your message" ></textarea>
        </fieldset>
        <div class="btn">
        <button class="button" type="submit" style="vertical-align:middle"><span>Send </span></button>
      </div>
      </form>
    </div>
<%@ include file = "footer.jsp" %> 
</body>
</html>