const formulario = document.getElementById("livroForm");
let livroEditando = null;

async function carregarLivros() {

    const resposta = await fetch("http://localhost:3000/livros");

    const livros = await resposta.json();

    const tabela = document.getElementById("tabelaLivros");

    tabela.innerHTML = "";

    livros.forEach(livro => {

        tabela.innerHTML += `
            <tr>
                <td>${livro.id}</td>
                <td>${livro.titulo}</td>
                <td>${livro.autor}</td>
                <td>${livro.genero}</td>
                <td>${livro.ano}</td>
                <td>${livro.quantidade}</td>
                <td>
                    <button onclick="excluirLivro(${livro.id})">
                        Excluir
                    </button>
                    <button onclick="editarLivro(${livro.id})">
                        Editar
                    </button>
                </td>
            </tr>
        `;

    });

}

formulario.addEventListener("submit", async (event) => {

    event.preventDefault();

    const titulo = document.getElementById("titulo").value;
    const autor = document.getElementById("autor").value;
    const genero = document.getElementById("genero").value;
    const ano = document.getElementById("ano").value;
    const quantidade = document.getElementById("quantidade").value;

    let url = "http://localhost:3000/livros";
    let metodo = "POST";

    if (livroEditando !== null) {
        url = `http://localhost:3000/livros/${livroEditando}`;
        metodo = "PUT";
    }

    const resposta = await fetch(url, {
        method: metodo,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            titulo,
            autor,
            genero,
            ano,
            quantidade
        })
    });

    const dados = await resposta.json();

    alert(dados.mensagem);

    formulario.reset();
    
    livroEditando = null;

    carregarLivros();

});

function editarLivro(id) {

    const linha = document.querySelector(
        `button[onclick="editarLivro(${id})"]`
    ).parentElement.parentElement;

    document.getElementById("titulo").value =
        linha.children[1].textContent;

    document.getElementById("autor").value =
        linha.children[2].textContent;

    document.getElementById("genero").value =
        linha.children[3].textContent;

    document.getElementById("ano").value =
        linha.children[4].textContent;

    document.getElementById("quantidade").value =
        linha.children[5].textContent;

    livroEditando = id;

}

async function excluirLivro(id) {

    const resposta = await fetch(
        `http://localhost:3000/livros/${id}`,
        {
            method: "DELETE"
        }
    );

    const dados = await resposta.json();

    alert(dados.mensagem);

    carregarLivros();

}

carregarLivros();