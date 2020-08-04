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