const express = require("express");
const cors = require("cors");
const db = require("./db");

const app = express();

app.use(cors());
app.use(express.json());

app.get("/", (req, res) => {
    res.send("Servidor da Biblioteca funcionando!");
});

app.post("/livros", (req, res) => {

    const { titulo, autor, genero, ano, quantidade } = req.body;

    db.run(
        `INSERT INTO livros (titulo, autor, genero, ano, quantidade)
         VALUES (?, ?, ?, ?, ?)`,
        [titulo, autor, genero, ano, quantidade],
        function (erro) {

            if (erro) {
                return res.status(500).json({
                    erro: erro.message
                });
            }

            res.status(201).json({
                mensagem: "Livro cadastrado com sucesso!",
                id: this.lastID
            });

        }
    );

});

app.get("/livros", (req, res) => {

    db.all(
        "SELECT * FROM livros",
        [],
        (erro, rows) => {

            if (erro) {
                return res.status(500).json({
                    erro: erro.message
                });
            }

            res.json(rows);

        }
    );

});

app.delete("/livros/:id", (req, res) => {

    const id = req.params.id;

    db.run(
        "DELETE FROM livros WHERE id = ?",
        [id],
        function (erro) {

            if (erro) {
                return res.status(500).json({
                    erro: erro.message
                });
            }

            res.json({
                mensagem: "Livro removido com sucesso!"
            });

        }
    );

});

app.put("/livros/:id", (req, res) => {

    const id = req.params.id;

    const { titulo, autor, genero, ano, quantidade } = req.body;

    db.run(
        `UPDATE livros
         SET titulo = ?, autor = ?, genero = ?, ano = ?, quantidade = ?
         WHERE id = ?`,
        [titulo, autor, genero, ano, quantidade, id],
        function (erro) {

            if (erro) {
                return res.status(500).json({
                    erro: erro.message
                });
            }

            res.json({
                mensagem: "Livro atualizado com sucesso!"
            });

        }
    );

});

app.listen(3000, () => {
    console.log("Servidor rodando em http://localhost:3000");
});