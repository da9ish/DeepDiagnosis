import os
from flask import *
import predict as pred
from werkzeug.utils import secure_filename

UPLOAD_FOLDER = './UPLOADFOLDER'
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg', 'bmp'])


app = Flask(__name__)
app._static_folder = "/static"
@app.route('/')
def homepage():

    return render_template("index.html")


@app.route('/upload/', methods=["GET","POST"])
def upload():
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            d = Response("Error",status=201,mimetype='application/json')
            return d
        file = request.files['file']
        # if user does not select file, browser also
        # submit a empty part without filename
        if file.filename == '':
            d = Response("No filename",status=201,mimetype='application/json')
            return d
        if file:
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return pred.classify(filename)
    return render_template("index.html")
    # if request.method == 'POST':
    #   f = request.files['file']
    #   f.save(secure_filename(f.filename))
    #   return render_template("index.html")

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000, debug=True)
    #app.run()
