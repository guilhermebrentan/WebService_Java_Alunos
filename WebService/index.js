const express = require('express'); 
const app = express();
const bodyParser = require('body-parser'); 
const port = 3000; //porta padrão const 
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

router.get('/executar/:RA?/:codDisciplina?/:nota?/:frequencia?', (req, resultado) => { //rota chamada pelo Java
    //inicio as variaveis usando a url
    var RA = parseInt(req.params.RA); 
    var codDisciplina = parseInt(req.params.codDisciplina);
    var nota = parseFloat(req.params.nota);
    var frequencia = parseFloat(req.params.frequencia);
    var pesquisa;
    var execute; 

    if(nota >= 0 && nota <= 10){ //valido se a nota está correta
        if(frequencia >= 0 && frequencia <= 1){ //valido a frequencia
            pesquisa = "select * from Alunos where RA = " + RA; //guardo as pesquisas de validação daquele momento nesta variavel
            global.conn.query(pesquisa, (req, res) => {
                if(res.rowsAffected != 0){ //se pelo menos 1 linha foi afetada, quer dizer que retornou algo

                    pesquisa = "select * from Disciplinas where cod = " + codDisciplina; //altero a pesquisa
                    global.conn.query(pesquisa, (req, res) =>{
                        if(res.rowsAffected != 0){ //vejo se afeta alguma linha

                            pesquisa = "select * from Matriculas where RA = " + RA + " and cod = " + codDisciplina;
                            global.conn.query(pesquisa, (req, res) => {
                                if(res.rowsAffected != 0){

                                    execute = "delete from Matriculas where RA = " + RA + " and cod = " + codDisciplina; //guardo os updates, inserts e deletes aqui
                                    global.conn.query(execute, (req, res) => {
                                        if(res.rowsAffected != 0){ //confiro se deletou 
                                            execute = "insert into Resultados values ("+ RA           + "," + 
                                                                                        codDisciplina + "," +
                                                                                        nota          + "," +
                                                                                        frequencia    + ")";

                                            global.conn.query(execute, (req, res) => {
                                                if(res.rowsAffected != 0) //confiro se salvou
                                                    resultado.json({"Mensagem": "Sucesso ao salvar Resultado!"}); //aviso que deu certo toda a operação
                                                else
                                                    resultado.json({"Mensagem": "Falha ao inserir Resultado. Tente mais tarde!"}); //ou se deu algo errado
                                            });
                                        }
                                        else{
                                            resultado.json({"Mensagem": "Falha ao excluir Matricula. Tente mais tarde"}); //se não deletar, aviso que é erro de conexão
                                        }
                                    });
                                }
                                else{
                                    resultado.json({"Mensagem": "Não existe esta matricula"}); //aviso que a matricula está ausente, caso não afete nenhuma linha
                                }
                            });
                        }
                        else{
                            resultado.json({"Mensagem": "Não existe esta disciplina"}); //aviso que a disciplina não existe
                        }
                    });
                }else{
                    resultado.json({"Mensagem": "Não existe este aluno"}); //aviso que não existe este aluno
                }
            });
        }
        else{
            resultado.json({"Mensagem": "Frequencia está fora de intervalo"}); //a frequencia é invalida
        }
    }
    else{
        resultado.json({"Mensagem": "Nota está fora de intervalo"}); //a nota é invalida
    }
});