$(function() {
    $('#resume-search').click(function() {
        $(this).closest('form').submit();
    });

    $('input#datetime').datepicker({
        format: "yyyy/mm/yy"
    });
});
