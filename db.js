const sqlite3 = require("sqlite3").verbose();

const db = new sqlite3.Database("./biblioteca.db", (erro) => {
    if (erro) {
        console.log("Erro ao conectar ao banco.");
    } else {
        console.log("Banco conectado com sucesso.");

        db.run(`
            CREATE TABLE IF NOT EXISTS livros (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                autor TEXT NOT NULL,
                genero TEXT NOT NULL,
                ano INTEGER,
                quantidade INTEGER
            )
        `);
    }
});

module.exports = db;