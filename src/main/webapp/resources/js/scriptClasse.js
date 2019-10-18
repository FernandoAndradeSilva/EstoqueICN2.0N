$("#formClasse\\:classes\\:filterCadastroItemClasse").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#formClasse\\:classes tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});



// $("#estMin").change(function () {
//
//     debugger;
//     var teste = $("#estMin > div > span").innerHTML;
//     alert(teste);
//
//
// });


// jQuery("#formCadastroItem\\:bt").click(function () {
//     alert('Clicadop');
// });


// $("#formCat\\:dtList\\:pesquisar").on("keyup", function () {
//     var value = $(this).val().toLowerCase();
//     $("#formCat\\:dtList div > ul > li").filter(function () {
//         $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
//     });
// });


// jQuery("#unMedida_items").click(function () {
//     var element = jQuery("select[name='unMedida_input'] option:selected").val();
//     alert(element);
//
//
//
// });
