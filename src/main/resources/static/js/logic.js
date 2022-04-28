jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});

$('.modal').on('shown.bs.modal', function() {
    $(this).find('[autofocus]').modal('show')
});

if(window.location.hash) {
    var hash = window.location.hash; //Puts hash in variable, and removes the # character
    $(window).on('load',function(){
        $(hash).modal('show')
    });
}

