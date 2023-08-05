// ajax call for registration
$(document).ready(function () {
    $("#signup-form").submit(function (event) {
        // Get the form data
        var uid="";
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var email = $("#Remail").val();
        var password = $("#registrationPassword").val();
        var phoneNumber = $("#phoneNumber").val();
        var gender = $("#gender").val();
        var dob = $("#dob").val();
        var country = $("#country").val();
        var subscription_id = $("#subscription").val();
          event.preventDefault();

        // Send the form data to the server using AJAX
        $.ajax({
            type: 'POST',
            url: '/bin/infodales/registration',
            dataType: 'text',
            data: { uid:uid,email:email,password:password,firstName:firstName,lastName:lastName,phoneNumber:phoneNumber,gender:gender,dob:dob,country:country,subscription_id:subscription_id},
            success: function (resultData, textStatus, jqXHR) {
                if (jqXHR.status == 200) {
                    alert("registration successful");
                    window.location.href = "/content/idmedia-aem/loginpage.html"; // redirect to home page
                } else if (jqXHR.status == 400) {
                    alert("registration failed profile already exist: " + resultData.message);
                } else {
                    alert("registration failed: " + resultData.message);
                }
            },
            error: function (resultData, textStatus, jqXHR) {
                alert("Something went wrong");
            },
        });
    });
});
