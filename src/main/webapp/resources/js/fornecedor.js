$("#filterFornecedor").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#fornecedores tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});


function limpa_formulário_cepPrincipal() {
    // Limpa valores do formulário de cep.
    $("#formCadastroFornecedor\\rua").val("");
    $("#formCadastroFornecedor\\bairro").val("");
    $("#formCadastroFornecedor\\cidade").val("");
    $("#formCadastroFornecedor\\uf").val("");
}

function limpa_formulário_cepEdicao() {
    // Limpa valores do formulário de cep.
    $('#'+form+'lograInput').val("");
    $('#'+form+'bairroInput').val("");
    $('#'+form+'cidInput').val("");
    $('#'+form+'estInput').val("");
    $('#'+form+'numInput').val("");
    $('#'+form+'compInput').val("");
}

$("#formCadastroFornecedor\\:cep").blur(function() {

    //Nova variável "cep" somente com dígitos.
    var cep = $(this).val().replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            $("#formCadastroFornecedor\\:rua").val("...");
            $("#formCadastroFornecedor\\:bairro").val("...");
            $("#formCadastroFornecedor\\:cidade").val("...");
            $("#formCadastroFornecedor\\:uf").val("...");

            //Consulta o webservice viacep.com.br/
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#formCadastroFornecedor\\:rua").val(dados.logradouro);
                    $("#formCadastroFornecedor\\:bairro").val(dados.bairro);
                    $("#formCadastroFornecedor\\:cidade").val(dados.localidade);
                    $("#formCadastroFornecedor\\:uf").val(dados.uf);

                } //end if.
                else {
                    //CEP pesquisado não foi encontrado.
                    limpa_formulário_cepPrincipal();
                    alert("CEP não encontrado.");
                }
            });
        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cepPrincipal();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cepPrincipal();
    }
});

function cep(a) {

    var form = a.id.toString().split(":");
    var form = form[0]+"\\:"+form[1]+"\\:";
    var cep = a.value.replace(/\D/g, '');


    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            $('#'+form+'lograInput').val("...");
            $('#'+form+'bairroInput').val("...");
            $('#'+form+'cidInput').val("...");
            $('#'+form+'estInput').val("...");
            $('#'+form+'numInput').val("...");
            $('#'+form+'compInput').val("...");


            //Consulta o webservice viacep.com.br/
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $('#'+form+'lograInput').val(dados.logradouro);
                    $('#'+form+'bairroInput').val(dados.bairro);
                    $('#'+form+'cidInput').val(dados.localidade);
                    $('#'+form+'estInput').val(dados.uf);
                    $('#'+form+'numInput').val("");
                    $('#'+form+'compInput').val("");

                } //end if.
                else {
                    //CEP pesquisado não foi encontrado.
                    limpa_formulário_cep();
                    alert("CEP não encontrado.");
                }
            });
        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }


}















