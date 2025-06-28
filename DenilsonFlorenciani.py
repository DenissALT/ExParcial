from flask import Flask

app = Flask(__name__)

@app.route('/saludar/DenilsonFlorenciani')
def saludar():
    return "<h1>Hola Denilson Florenciani que tal!</h1>"
if __name__ == '__main__':
    app.run(debug=True)
