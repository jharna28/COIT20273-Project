/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function(){
    
       var url = "api/user/getuser";

                    var userid = $.cookie("userid");
                    

                    var formData = {UserId: userid};

                    $.ajax({
                        cache: false,
                        url: url,
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(formData),
                        contentType: "application/json",
                        success: function (data) {

                           $("#username").html(data.FirstName+" "+ data.LastName);
                           if(data.IsHelper == true)
                           {
                               $("#label1").html("Helper ");
                               $("#consult").hide();
                               $("#resource").hide(); 
                               $("#appointmentseeker").hide();
                               $("#appointmenthelper").show();
                               $("#helperImage").show();
                           }
                           else
                           {
                               $("#label1").html("Seeker ");
                               $("#consult").show();
                               $("#resource").show();
                               $("#appointmentseeker").show();
                               $("#appointmenthelper").hide();
                               $("#seekerImage").show();
                           }
                         
                        },
                        error: function (jqXHR, exception) {
                            $("#message").addClass("text-danger");
                            $("#message").html(exception);

                        }

                    });
});