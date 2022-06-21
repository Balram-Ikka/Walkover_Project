<%@include file="/template/header.jsp" %>

      <form class="form-signin" action="inscription" method="post">
        <h2 class="form-signin-heading">Registration</h2>
        <input type="email" class="input-block-level" placeholder="Email" id="username" name="username">
        <input type="password" class="input-block-level" placeholder="Create Password" id="password" name="password">
        <input type="password" class="input-block-level" placeholder="Confirm Password " id="password2" name="password2">
        

        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
      </form>
<%@include file="/template/footer.jsp" %>