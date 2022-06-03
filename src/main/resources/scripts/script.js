function exibeApp(){
    let rowApp = document.getElementById('colApp');
    let texto = '';
    let pesquisa = document.getElementById('pesquisa_app');
    let dadosp = JSON.parse(this.responseText);
    let filtroNota = document.getElementById('filtroNota');
    let filtroCategoria = document.getElementById('filtroCategoria');
    let filtroDispositivo = document.getElementById('filtroDispositivo');
    let filtroPago = document.getElementById('filtroPago');
    let dados = dadosp.filter(item => parseFloat(item.nota) > filtroNota.options[filtroNota.selectedIndex].value);
    if (filtroDispositivo.options[filtroDispositivo.selectedIndex].value != " "){
        dados = dados.filter(item => item.dispositivo == (filtroDispositivo.options[filtroDispositivo.selectedIndex].value));
    }
    if (filtroDispositivo.options[filtroCategoria.selectedIndex].value != " "){
        dados = dados.filter(item => item.categoria == (filtroCategoria.options[filtroCategoria.selectedIndex].value));
    }
    if (filtroDispositivo.options[filtroPago.selectedIndex].value != " "){
        if(dados.pago == false){
            dados.pago = "Gratuito";
        }
        else{
            dados.pago = "Pago";
        }
        dados = dados.filter(item => item.pago == (filtroPago.options[filtroPago.selectedIndex].value));
    }
    if(pesquisa.value != ""){
        dados = dados.filter(item => item.nome.includes(pesquisa.value));
    }
    for (i=0; i<dados.length;i++){
        let app = dados[i];
        texto = texto + `
            <div class="applicativo">
            <div class="row" id="app">
            <div class="col-12 col-md-3" id="picapp">
                <img id="imageapli" src="${app.imagem}" height="150" width="150" alt=""><br>
                <p id="nota"><span>Nota:</span>
                <span>${app.nota}</span></p>
            </div>
            <div class="col-md-9 col-12" id="infoapli">
                <h2>${app.nome}</h2>
                <p><span>Dispositivo:</span> ${app.dispositivo}</p>
                <p><span>Descrição:</span> ${app.descricao}</p>
                <div class="mb-3">
                    <a class="btn btn-success" onclick="AppPage(${app.id})" role="button">Ver mais</a>
                </div>
            </div>
                <div id="linha-horizontal"></div>
            </div>
        `;
    };

    rowApp.innerHTML = texto;
}

function initApp(){
    let rowApp = document.getElementById('itemsdocar');
    let texto = '';
    let dados = JSON.parse(this.responseText);
    for (i=0; i<4;i++){
        let app = dados[i];
        texto = texto + `
            <div class="col-sm-3">

            <div class="card">
            <div class="card-body">
                <img src="${app.imagem}" height="100" width="100" alt="">
                <h5 class="card-title">${app.nome}</h5>
                <p class="card-text"><span>Descrição:</span> ${app.descricao} <br><span>
                    Nota:</span> ${app.nota}/10</p>
                <a onclick="AppPage(${app.id})" class="btn btn-success">Ver</a>
            </div>
            </div>
        </div>
        `;
    };

    rowApp.innerHTML = texto;
}

function listarApps (){
    let xhr = new XMLHttpRequest ();
    xhr.onload = exibeApp;
    xhr.open('GET','http://localhost:4567/Aplicativo');
    xhr.send();
}

function destaqueApps (){
    let xhr2 = new XMLHttpRequest ();
    xhr2.onload = initApp;
    xhr2.open('GET','http://localhost:4567/Aplicativo');
    xhr2.send();
}

let globalListaUsers = "";

function carregaPFV(){
    globalListaUsers = JSON.parse(this.responseText);
    console.log(globalListaUsers);
}

function chargeUserStart(){
    let xhr7 = new XMLHttpRequest ();
    xhr7.onload = carregaPFV;
    xhr7.open('GET','http://localhost:4567/Usuario');
    xhr7.send();
}

function chargeComments(id2){
    console.log(id2);
    console.log("prequest");
    let usuarioname = "";
    let texto2 = '';
    let xhr4 = new XMLHttpRequest();
    xhr4.onload = function carregaComents(){
        let dadoss = JSON.parse(this.responseText);
        console.log(dadoss);
        console.log(dadoss.length);
        dadoss = dadoss.filter(item => parseInt(item.aplicativo_id) == id2);
        console.log(dadoss);
        console.log(dadoss.length);
        for (var i = 0; i < dadoss.length; i++){
            let c = dadoss[i];
            for(var j = 0; j < globalListaUsers.length; j++){
                console.log(j);
                if(c.user_id == globalListaUsers[j].id){
                    usuarioname = globalListaUsers[j].nome;
                    texto2 = texto2+`
                        <h5>${usuarioname}</h5>
                        <textarea disabled name="comment" id="" cols="10" rows="5">${c.texto}</textarea>
                        `;
                }
                console.log(texto2);
            }
        }
    };
    xhr4.open('GET', 'http://localhost:4567/Comentario', false);
    xhr4.send();
    console.log(texto2);
    return texto2;
}

function AppPage(id2){
    let texto22 = '';
    texto22 = chargeComments(id2);
    console.log(texto22);
    let xhr3 = new XMLHttpRequest();
    xhr3.onload = function Page(){
        console.log("fala");
        let dados = JSON.parse(this.responseText);
        let w = window.open('app.html', '_blank');
        let texto = '';
        let app = dados;
        texto = texto + `
        <!doctype html>
        <html lang="pt-br">
          <head>
            <title>${app.nome}</title>
            <!-- Required meta tags -->
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
            <script src="https://kit.fontawesome.com/4498db3338.js" crossorigin="anonymous"></script>
            <link href="styles/style.css" rel="stylesheet">
            <body>
                <div class="header">
                    <nav class="navbar navbar-expand-lg navbar-dark fixed-top">
                        <div class="container-fluid">
                          <a class="navbar-brand" href="#"><img id="imagemi" src="assets/eadtools_white-removebg.png" class="img" alt="..."></a>
                          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                          </button>
                          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                              <a class="nav-link" aria-current="page" href="index.html">Home</a>
                              <a class="nav-link active" aria-current="page" href="apli.html">Aplicações</a>
                              <a class="nav-link" aria-current="page" id="loglink" href="login.html">Login</a>
                            </div>
                          </div>
                        </div>
                      </nav>
                </div>
                <div class="container-fluid" id="containerGeral">
                    <div class="row">
                        <div class="col-12 col-md-2" id="filtros">
                          <div class="appinfo_apppage" id="appinfo_apppage">
                            <img src="${app.imagem}" width="170" alt="">
                            <h2>${app.nome}</h2>
                            <p>Nota:</p>
                            <p><span>${app.nota}/10</span></p>
                            <p>Dispositivo:</p>
                            <p>${app.dispositivo}</p>
                          </div>
                        </div>
                        <div class="col">
                            <div class="applicativo" id="colaApp">
                                <div class="row" id="app">
                                    <h3>Sobre o App</h3>
                                    <p>${app.descricao}</p>
                                    <iframe width='560' height='515' src='${app.tutorial}' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>
                                    <h3>Onde encontrar</h3>
                                    <p>Vá para a página da aplicação clicando aqui: <a href="${app.fonte}">${app.fonte}</a></p>
                                    <h3>Recursos</h3>
                                    <p>${app.dicas}</p>
                                    <div class="row" id="commentarea">
                                    <h3>Comentários</h3>
                                    <textarea name="comment" id="mycomment" cols="10" rows="5"></textarea>
                                    <a href="#" id="comentar" onclick="comentar(${id2})" class="btn btn-success">Comentar</a>
                                    ${texto22}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <script src="scripts/script.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
          </body>
        </html>
        `;
        w.document.write(`<p>${texto}</p>`);
        verifyLogged();
    };
    xhr3.open('GET','http://localhost:4567/Aplicativo/'+id2);
    xhr3.send();
}

document.onload = listarApps();
document.onload = destaqueApps();
document.onload = chargeUserStart();
document.onload = verifyLogged();


//////////////////////////////////////////////////////////////////////////////

//
//
// Disciplina: Trabalho Interdisciplinar - Aplicações Web
// Professor: Rommel Vieira Carneiro (rommelcarneiro@gmail.com)
//
// Código LoginApp utilizado como exemplo para alunos de primeiro período 


// Página inicial de Login
const LOGIN_URL = "login.html";

//functions latter added

function verifyLogged(){
    var logado = localStorage.getItem('logado');
    if (!logado) {  
        logado = 0;
        localStorage.setItem('logado', JSON.stringify (logado));
    }
    else  {
        logado = JSON.parse(logado);
        let navlog = document.getElementById("loglink");
        var comentar_permit = document.getElementById("mycomment");
        var comentar_button = document.getElementById("comentar");
        if(logado == 1){
            let navlog = document.getElementById("loglink");
            comentar_permit = document.getElementById("mycomment");
            navlog.innerText = "Sair";
            navlog.setAttribute("href","index.html");
            navlog.setAttribute("onclick","logoutUser()");
            comentar_permit.setAttribute("enabled", "true")
        }
        else{
            navlog.innerText = "Login";
            navlog.setAttribute("href","login.html");
            navlog.setAttribute("onclick","");
            comentar_permit.setAttribute("disabled", "true")
            comentar_button.setAttribute("disabled","true");
        }
    }
};

// Objeto para o usuário corrente
var usuarioCorrente = {};



function initLoginApp () {
    // PARTE 1 - INICIALIZA USUARIOCORRENTE A PARTIR DE DADOS NO LOCAL STORAGE, CASO EXISTA
    usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
    if (usuarioCorrenteJSON) {
        usuarioCorrente = JSON.parse(usuarioCorrenteJSON);
    }
};

// Verifica se o login do usuário está ok e, se positivo, direciona para a página inicial
function loginUser (login, senha) {
    // Verifica todos os itens do banco de dados de usuarios 
    // para localizar o usuário informado no formulario de login
    let usuario = {};
    for (let i = 0; i < globalListaUsers.length; i++) {
        usuario = globalListaUsers[i];
        if (login == usuario.nome && senha == usuario.senha) {
            logado = 1;
            localStorage.setItem('logado', JSON.stringify (logado));
            //
            usuarioCorrente = usuario;
            // Salva os dados do usuário corrente no Session Storage, mas antes converte para string
            sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));

            // Retorna true para usuário encontrado
            return true;
        }
    }
    // Se chegou até aqui é por que não encontrou o usuário e retorna falso
    return false;
}

// Apaga os dados do usuário corrente no sessionStorage
function logoutUser () {
    logado = 0;
    localStorage.setItem('logado', JSON.stringify (logado));
    let navlog = document.getElementById("loglink");
    navlog.innerText = "Login";
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

function addUser (nome, senha, email) {
    let xhr8 = new XMLHttpRequest ();
    xhr8.open('POST','http://localhost:4567/Usuario?nome='+nome+'&email='+email+'&senha='+senha);
    xhr8.send();
}


function comentar(id3) {
    var texto = document.getElementById('mycomment').value;
    var autor = JSON.parse(sessionStorage.getItem('usuarioCorrente')).id;
    const d = new Date();
    let data = d.toISOString().substring(0, 10);
    console.log(data);
    let xhr9 = new XMLHttpRequest ();
    xhr9.open('POST','http://localhost:4567/Comentario?texto='+texto+'&data='+data+'&aplicativo_id='+id3+'&user_id='+autor);
    xhr9.send();
    AppPage(id3);
}

//function setUserPass () {

//}

// Inicializa as estruturas utilizadas pelo LoginApp
initLoginApp ();

