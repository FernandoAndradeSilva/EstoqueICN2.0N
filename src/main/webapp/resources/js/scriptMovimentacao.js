$("#filterMovimentacao").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#movimentacoes tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});


