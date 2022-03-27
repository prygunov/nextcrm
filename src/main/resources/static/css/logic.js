// Act on clicks to a elements
$("#link1").on('click', function(e) {
    // prevent the default action, in this case the following of a link
    e.preventDefault();
    // capture the href attribute of the a element
    var url = $(this).attr('href');
    // perform a get request using ajax to the captured href value
    $.get(url, function() {
        // success
    });
});