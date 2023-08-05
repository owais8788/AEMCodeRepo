$(document).ready(function() {
  $('#login-form').submit(function(e) {
    var email = $('#email').val();
    var password = $('#loginPassword').val();
    e.preventDefault();
    $.ajax({
      type: 'POST',
      url: '/bin/infodales/userlogin',
      dataType:'text',
      data: {email: email, password: password},
      success: function(resultData, textStatus, jqXHR) {
        if (jqXHR.status == 200) {
          alert('Login successful');
          window.location.href = '/content/idmedia-aem/us/en/homepage.html'; // redirect to home page
        } else {
          alert('Login failed: ' + resultData.message);
        }
      },
      error: function(resultData, textStatus, jqXHR) {
        alert('Something went wrong');
      }
    });
  });
});
