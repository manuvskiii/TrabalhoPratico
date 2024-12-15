from flask import Flask, jsonify
import pymysql

app = Flask(__name__)

# Conexão com a base de dados no XAMPP
db = pymysql.connect(
    host="localhost",
    user="root",
    password="",  # Deixa vazio se não configuraste uma password no MySQL
    database="EstagiosIPVC"
)

# Endpoint para listar estágios
@app.route('/api/estagios', methods=['GET'])
def get_estagios():
    cursor = db.cursor(pymysql.cursors.DictCursor)
    query = """
        SELECT e.id_estagio, e.empresa, e.descricao, c.nome AS curso, l.cidade, l.distrito
        FROM estagios e
        INNER JOIN cursos c ON e.id_curso = c.id_curso
        INNER JOIN locais l ON e.id_local = l.id_local;
    """
    cursor.execute(query)
    results = cursor.fetchall()
    return jsonify(results)

# Endpoint para listar cursos
@app.route('/api/cursos', methods=['GET'])
def get_cursos():
    cursor = db.cursor(pymysql.cursors.DictCursor)
    query = "SELECT * FROM cursos"
    cursor.execute(query)
    results = cursor.fetchall()
    return jsonify(results)

# Endpoint para listar locais
@app.route('/api/locais', methods=['GET'])
def get_locais():
    cursor = db.cursor(pymysql.cursors.DictCursor)
    query = "SELECT * FROM locais"
    cursor.execute(query)
    results = cursor.fetchall()
    return jsonify(results)

# Endpoint para listar escolas
@app.route('/api/escolas', methods=['GET'])
def get_escolas():
    cursor = db.cursor(pymysql.cursors.DictCursor)
    query = "SELECT * FROM escolas"
    cursor.execute(query)
    results = cursor.fetchall()
    return jsonify(results)

# Iniciar o servidor Flask
if __name__ == '__main__':
    app.run(debug=True, port=5000)