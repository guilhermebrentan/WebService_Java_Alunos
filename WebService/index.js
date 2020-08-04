const express = require('express'); 
const app = express();
const bodyParser = require('body-parser'); 
const port = 3000; //porta padrãoconst 
sql = require('mssql');
const connStr = "Server=regulus.cotuca.unicamp.br;Database=BD19175;User Id=bd19175;Password=guidomotoca45;"


sql.connect(connStr)
.then(conn => global.conn = conn)
.catch(err => console.log(err));

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const router = express.Router();

router.get('/', (req, res) => res.json({"Mensagem": "A API está online"}));
app.use("/", router);

app.listen(port);
console.log("O servidor está online!");

// rotas da aplicação

router.get('/executar/:RA?/:codDisciplina?/:nota?/:frequencia?', (req, res) => {
    var RA = parseInt(req.params.RA);
    var codDisciplina = parseInt(req.params.codDisciplina);
    var nota = parseFloat(req.params.nota);
    var frequencia = parseFloat(req.params.frequencia);

    var condicao = "where RA = " + RA + " and Cod = " + codDisciplina;
    var comandoSql = 'delete from Matriculas ' + condicao;

    execSQLQuery(comandoSql, res);

    comandoSql ='insert into Resultados values(' + RA + "," + codDisciplina + "," + nota + "," + frequencia + ')';

    execSQLQuery(comandoSql, res);
});

//métodos adicionais
function execSQLQuery(sqlQry, res){
    global.conn.request()
    .query(sqlQry)
    .then(result => res.json(result.recordset))
    .catch(err => res.json(err));
}