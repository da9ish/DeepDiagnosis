import os
from flask import *
from returndata import Content
from werkzeug.utils import secure_filename

UPLOAD_FOLDER = './UPLOADFOLDER'
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg', 'bmp'])



TOPIC_DICT = Content()
app = Flask(__name__)
app._static_folder = "/static"
@app.route('/')
def homepage():

    return render_template("index.html")

@app.route('/login/', methods=["GET","POST"])
def login():
    error = ''
    # try:

    if request.method == "POST":

        attempted_username = request.form['username']
        attempted_password = request.form['password']



        if attempted_username == "admin" and attempted_password == "password":
            return redirect(url_for('dashboard'))

        else:
            error = "Invalid credentials. Try Again."

    return render_template("login.html", error = error)



@app.route('/dashboard/')
def dashboard():
    return render_template("dashboard.html", TOPIC_DICT = TOPIC_DICT)


@app.route('/upload/', methods=["GET","POST"])
def upload():
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            # #flash('No file part')
            # return redirect(url_for('login'))
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            flash('No selected file')
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            d = Response("File arrived",status=200,mimetype='application/json')
            return d
    return render_template("index.html")
    # if request.method == 'POST':
    #   f = request.files['file']
    #   f.save(secure_filename(f.filename))
    #   return render_template("index.html")

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000, debug=False)
    #app.run()
